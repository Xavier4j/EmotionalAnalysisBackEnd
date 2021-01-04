package club.doyoudo.emotional.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.nlp.ESimnetType;

import java.util.HashMap;

public interface NlpService {
    /**
     * 情感倾向分析，用来对帖子下的评论回复进行情感倾向分析
     *
     * @param text
     * @param options
     * @return
     */
    JSONObject sentimentClassify(String text, HashMap options);

    /**
     * 输入两个词，得到两个词的相似度结果。这个不太好用 暂时未用到
     * @param word1
     * @param word2
     * @param options
     * @return
     */
    JSONObject wordSimEmbedding(String word1, String word2, HashMap options);

    /**
     * 输入两个文本，得到两个文本的相似度结果。
     * @param text1
     * @param text2
     * @param options
     * @return
     */
    JSONObject simnet(String text1, String text2, HashMap options);

    /**
     * 评论观点抽取，用来展示某些舆情的评论观点标签及评论观点极性
     *
     * @param text
     * @param eSimnetType
     * @param options
     * @return
     */
    JSONObject commentTag(String text, ESimnetType eSimnetType, HashMap options);

    /**
     * 文章标签 用来展示论坛热点
     *
     * @param title
     * @param content
     * @param options
     * @return
     */
    JSONObject keyword(String title, String content, HashMap options);

    /**
     * 文章分类，用来展示论坛讨论热门讨论话题
     *
     * @param title
     * @param content
     * @param options
     * @return
     */
    JSONObject topic(String title, String content, HashMap options);

    /**
     * 文本纠错 暂时无大用处
     *
     * @param text
     * @param options
     * @return
     */
    JSONObject ecnet(String text, HashMap options);

    /**
     * 新闻摘要接口，可以自动生成文章的摘要
     *
     * @param content
     * @param maxSummaryLen
     * @param options
     * @return
     */
    JSONObject newsSummary(String content, int maxSummaryLen, HashMap options);

}
