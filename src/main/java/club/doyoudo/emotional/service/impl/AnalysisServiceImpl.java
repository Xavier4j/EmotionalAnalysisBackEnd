package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.AnalysisSentimentMapper;
import club.doyoudo.emotional.mapper.CommentMapper;
import club.doyoudo.emotional.mapper.PhoneMapper;
import club.doyoudo.emotional.model.*;
import club.doyoudo.emotional.pojo.AnalysisSentimentWithPhone;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.service.AnalyseCommentTagService;
import club.doyoudo.emotional.service.AnalyseSentimentService;
import club.doyoudo.emotional.service.AnalysisService;
import club.doyoudo.emotional.service.NlpService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.nlp.ESimnetType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    PhoneMapper phoneMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    AnalysisSentimentMapper analysisSentimentMapper;
    @Resource
    NlpService nlpServiceImpl;
    @Resource
    AnalyseSentimentService analyseSentimentServiceImpl;
    @Resource
    AnalyseCommentTagService analyseCommentTagServiceImpl;

    public void sleep() {
        try {
            Thread.sleep( 600 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void analyseSentimentDaily(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays( 1 ).atStartOfDay();

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andCreateTimeBetween( start, end );
        List<Comment> commentList = commentMapper.selectByExample( commentExample );

        HashMap<String, Object> options = new HashMap<>();

        commentList.forEach( comment -> {
            //如果当前评论未分析过，则将数据持久化
            AnalysisSentiment analysisSentiment = analysisSentimentMapper.selectByPrimaryKey( comment.getId() );
            if (analysisSentiment == null) {
                JSONObject jsonObject = nlpServiceImpl.sentimentClassify( comment.getContent(), options );
                System.out.println( jsonObject );
                sleep();

                JSONArray items = jsonObject.getJSONArray( "items" );
                JSONObject item = items.getJSONObject( 0 );
                analysisSentiment = new AnalysisSentiment();
                analysisSentiment.setId( comment.getId() );
                analysisSentiment.setPhoneId( comment.getPhoneId() );
                analysisSentiment.setCreateTime( comment.getCreateTime() );
                analysisSentiment.setSentiment( item.getIntValue( "sentiment" ) );
                analysisSentiment.setPositiveProb( item.getDouble( "positive_prob" ) );
                analysisSentiment.setNegativeProb( item.getDouble( "negative_prob" ) );
                analysisSentiment.setConfidence( item.getDouble( "confidence" ) );
                analyseSentimentServiceImpl.insertAnalysisSentiment( analysisSentiment );
            }
        } );
    }

    @Override
    public ResponseWrapper analyseWholeSentiment(LocalDateTime start, LocalDateTime end) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        List<Map<String, Object>> result = new ArrayList<>();

        for (; startDate.isBefore( endDate ); startDate = startDate.plusDays( 1 )) {
            AnalysisSentimentExample analysisSentimentExample = new AnalysisSentimentExample();
            analysisSentimentExample.createCriteria().andCreateTimeBetween( startDate.atStartOfDay(), startDate.plusDays( 1 ).atStartOfDay() );
            List<AnalysisSentiment> analysisSentimentList = analysisSentimentMapper.selectByExample( analysisSentimentExample );

            //对每天的结果进行分析处理
            Double sentimentSum = 0D;
            Double positiveProbSum = 0D;
            for (AnalysisSentiment analysisSentiment : analysisSentimentList) {
                sentimentSum += analysisSentiment.getSentiment();
                //数据处理思路：基于准确性，positiveProb应该通过一定算法在confidence的作用下向0.5（情感偏中性）靠近
                positiveProbSum += (analysisSentiment.getPositiveProb() - 0.5) * analysisSentiment.getConfidence() + 0.5;
            }

            System.out.println( "sentimentSum" + sentimentSum );
            System.out.println( "positiveProbSum" + positiveProbSum );

            Double sentimentAverage;
            Double positiveProbAverage;

            if (analysisSentimentList.size() == 0) {
                sentimentAverage = 1.0;
                positiveProbAverage = 0.5;
            } else {
                sentimentAverage = sentimentSum / analysisSentimentList.size();
                positiveProbAverage = positiveProbSum / analysisSentimentList.size();
            }
            //保留后四位小数，四舍五入
            sentimentAverage = new BigDecimal( sentimentAverage ).setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
            positiveProbAverage = new BigDecimal( positiveProbAverage * 100 ).setScale( 0, BigDecimal.ROUND_HALF_UP ).doubleValue();

            HashMap<String, Object> map = new HashMap<>();
            map.put( "date", startDate );
            map.put( "value", positiveProbAverage );
            result.add( map );
        }

        return new ResponseWrapper( true, 200, "成功", result );
    }

    @Override
    public ResponseWrapper analyseNegative(LocalDateTime start, LocalDateTime end, Integer num) {

        AnalysisSentimentExample analysisSentimentExample = new AnalysisSentimentExample();
        analysisSentimentExample.createCriteria().andCreateTimeBetween( start, end );
        analysisSentimentExample.setOrderByClause( "negative_prob desc" );

        Page<AnalysisSentimentWithPhone> page = PageHelper.startPage( 1, num );
        List<AnalysisSentiment> analysisSentimentList = analysisSentimentMapper.selectByExample( analysisSentimentExample );
        PageInfo<AnalysisSentimentWithPhone> pageInfo = page.toPageInfo();

        Iterator<AnalysisSentiment> it = analysisSentimentList.iterator();
        List<AnalysisSentimentWithPhone> analysisSentimentWithPhoneList = new ArrayList<>();
        while (it.hasNext()) {
            AnalysisSentiment analysisSentiment = it.next();

            Phone phone = phoneMapper.selectByPrimaryKey( analysisSentiment.getPhoneId() );

            //将FilterRecord封装成FilterRecordWithProfile
            AnalysisSentimentWithPhone analysisSentimentWithPhone = JSONObject.parseObject( JSONObject.toJSONString( analysisSentiment ), AnalysisSentimentWithPhone.class );
            //设置新属性
            analysisSentimentWithPhone.setPhone( phone );

            analysisSentimentWithPhoneList.add( analysisSentimentWithPhone );
        }
        pageInfo.setList( analysisSentimentWithPhoneList );

        return new ResponseWrapper( true, 200, "成功", pageInfo );
    }

    @Override
    public Map<String, Double> analyseSentiment(String phoneId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andPhoneIdEqualTo( phoneId );
        List<Comment> commentList = commentMapper.selectByExample( commentExample );

        HashMap<String, Object> options = new HashMap<>();
        List<AnalysisSentiment> analysisSentimentList = new ArrayList<>();

        commentList.forEach( comment -> {
            //如果当前评论未分析过，则将数据持久化
            AnalysisSentiment analysisSentiment = analysisSentimentMapper.selectByPrimaryKey( comment.getId() );
            if (analysisSentiment == null) {
                JSONObject jsonObject = nlpServiceImpl.sentimentClassify( comment.getContent(), options );
//                System.out.println( jsonObject );
                sleep();

                JSONArray items = jsonObject.getJSONArray( "items" );
                JSONObject item = items.getJSONObject( 0 );
                analysisSentiment = new AnalysisSentiment();
                analysisSentiment.setId( comment.getId() );
                analysisSentiment.setPhoneId( phoneId );
                analysisSentiment.setSentiment( item.getIntValue( "sentiment" ) );
                analysisSentiment.setPositiveProb( item.getDouble( "positive_prob" ) );
                analysisSentiment.setNegativeProb( item.getDouble( "negative_prob" ) );
                analysisSentiment.setConfidence( item.getDouble( "confidence" ) );
                analyseSentimentServiceImpl.insertAnalysisSentiment( analysisSentiment );
            }
            analysisSentimentList.add( analysisSentiment );
        } );

        //对结果进行分析处理
        Double sentimentSum = 0D;
        Double positiveProbSum = 0D;
        Double positiveCommentNum = 0D;
        Double neutralCommentNum = 0D;
        Double negativeCommentNum = 0D;
        for (AnalysisSentiment analysisSentiment : analysisSentimentList) {
            sentimentSum += analysisSentiment.getSentiment();
            if (analysisSentiment.getSentiment() == 2) {
                positiveCommentNum++;
            } else if (analysisSentiment.getSentiment() == 1) {
                neutralCommentNum++;
            } else if (analysisSentiment.getSentiment() == 0) {
                negativeCommentNum++;
            }
            //数据处理思路：基于准确性，positiveProb应该通过一定算法在confidence的作用下向0.5（情感偏中性）靠近
            positiveProbSum += (analysisSentiment.getPositiveProb() - 0.5) * analysisSentiment.getConfidence() + 0.5;
        }

        Double sentimentAverage;
        Double positiveProbAverage;

        if (analysisSentimentList.size() == 0) {
            sentimentAverage = 1.0;
            positiveProbAverage = 0.5;
        } else {
            sentimentAverage = sentimentSum / analysisSentimentList.size();
            positiveProbAverage = positiveProbSum / analysisSentimentList.size();
        }
        //保留后四位小数，四舍五入
        sentimentAverage = new BigDecimal( sentimentAverage ).setScale( 2, RoundingMode.HALF_UP ).doubleValue();
        positiveProbAverage = new BigDecimal( positiveProbAverage ).setScale( 2, RoundingMode.HALF_UP ).doubleValue();

        Map<String, Double> map = new HashMap();
        map.put( "sentimentAverage", sentimentAverage * 100 );
        map.put( "positiveProbAverage", positiveProbAverage * 100 );
        map.put( "positiveCommentNum", positiveCommentNum );
        map.put( "neutralCommentNum", neutralCommentNum );
        map.put( "negativeCommentNum", negativeCommentNum );

        return map;
    }

    @Override
    public void analyseCommentTagDaily(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays( 1 ).atStartOfDay();
        PhoneExample phoneExample = new PhoneExample();
        phoneExample.createCriteria().andReleaseTimeBetween( start, end );
        List<Phone> phoneList = phoneMapper.selectByExample( phoneExample );
        for (Phone phone : phoneList) {
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andPhoneIdEqualTo( phone.getId() );
            List<Comment> commentList = commentMapper.selectByExample( commentExample );

            HashMap<String, Object> options = new HashMap<>();

            List<AnalysisCommentTag> commentTagList = new ArrayList<>();

            //处理comment
            for (Comment comment : commentList) {
                String content = comment.getContent();
                List<AnalysisCommentTag> analysisCommentTagList = analyseCommentTagServiceImpl.selectAnalysisCommentTag( phone.getId(), comment.getId() );
                if (analysisCommentTagList.size() == 0) {
                    JSONObject jsonObject = nlpServiceImpl.commentTag( content, ESimnetType._3C, options );
                    System.out.println( jsonObject );
                    JSONArray items = jsonObject.getJSONArray( "items" );
                    items.iterator();
                    items.forEach( itemObj -> {
                        JSONObject item = JSONObject.parseObject( itemObj.toString() );
                        AnalysisCommentTag analysisCommentTag = new AnalysisCommentTag();
                        String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );
                        analysisCommentTag.setId( uuid );
                        analysisCommentTag.setPhoneId( phone.getId() );
                        analysisCommentTag.setCommentId( comment.getId() );
                        analysisCommentTag.setSentiment( item.getIntValue( "sentiment" ) );
                        analysisCommentTag.setAdj( item.getString( "adj" ) );
                        analysisCommentTag.setProp( item.getString( "prop" ) );
                        analysisCommentTag.setAbstractText( item.getString( "abstract" ) );
                        analyseCommentTagServiceImpl.insertAnalysisCommentTag( analysisCommentTag );
                        analysisCommentTagList.add( analysisCommentTag );
                        commentTagList.addAll( analysisCommentTagList );
                    } );
                    if (items.size() == 0) {
                        AnalysisCommentTag analysisCommentTag = new AnalysisCommentTag();
                        String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );
                        analysisCommentTag.setId( uuid );
                        analysisCommentTag.setPhoneId( phone.getId() );
                        analysisCommentTag.setCommentId( comment.getId() );
                        analysisCommentTag.setSentiment( -1 );
                        analyseCommentTagServiceImpl.insertAnalysisCommentTag( analysisCommentTag );
                    }
//                System.out.println( content );
//                System.out.println( eSimnetType );
//                System.out.println( jsonObject );
                    sleep();
                }
            }
            for (AnalysisCommentTag analysisCommentTag : commentTagList) {
                CommentTag commentTag = new CommentTag();
                commentTag.setPhoneId( phone.getId() );
                commentTag.setCount( 1 );
                commentTag.setTag( analysisCommentTag.getProp() + analysisCommentTag.getAdj() );
            }
        }
    }
}
