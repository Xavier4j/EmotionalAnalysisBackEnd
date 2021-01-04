package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.pojo.Nlp;
import club.doyoudo.emotional.service.NlpService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.nlp.ESimnetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NlpServiceImpl implements NlpService {
    Nlp nlp;

    @Autowired(required = false)
    public NlpServiceImpl(Nlp nlp) {
        this.nlp = nlp;
    }


    @Override
    public JSONObject sentimentClassify(String text, HashMap options) {
        // 情感倾向分析
        JSONObject res = JSON.parseObject( nlp.getClient().sentimentClassify( text, options ).toString() );
        return res;
    }

    @Override
    public JSONObject wordSimEmbedding(String word1, String word2, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().wordSimEmbedding( word1, word2, options ).toString() );
        return res;
    }

    @Override
    public JSONObject simnet(String text1, String text2, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().simnet( text1, text2, options ).toString() );
        return res;
    }

    @Override
    public JSONObject commentTag(String text, ESimnetType eSimnetType, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().commentTag( text, eSimnetType, options ).toString() );
        return res;
    }

    @Override
    public JSONObject keyword(String title, String content, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().keyword( title, content, options ).toString() );
        return res;
    }

    @Override
    public JSONObject topic(String title, String content, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().topic( title, content, options ).toString() );
        return res;
    }

    @Override
    public JSONObject ecnet(String text, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().ecnet( text, options ).toString() );
        return res;
    }

    @Override
    public JSONObject newsSummary(String content, int maxSummaryLen, HashMap options) {
        JSONObject res = JSON.parseObject( nlp.getClient().newsSummary( content, maxSummaryLen, options ).toString() );
        return res;
    }

}
