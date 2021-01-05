package club.doyoudo.emotional;

import club.doyoudo.emotional.model.Phone;
import club.doyoudo.emotional.model.PhoneExample;
import club.doyoudo.emotional.service.AnalysisService;
import club.doyoudo.emotional.service.PhoneService;
import club.doyoudo.emotional.utils.ImportComment;
import club.doyoudo.emotional.utils.ScheduledTask;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ForumApplicationTests {
    @Resource
    ImportComment importComment;
    @Resource
    PhoneService phoneServiceImpl;
    @Resource
    AnalysisService analysisServiceImpl;

    @Test
    public void importCommentTask() {
        Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
        logger.info("analyseSentimentDailyTask 开始 ByCorn：" + LocalDateTime.now());
        importComment.runImportComment();
        logger.info("analyseSentimentDailyTask 结束 ByCorn：" + LocalDateTime.now());
    }

    @Test
    public void analysisCommentTask() {
        Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
        logger.info("analyseSentimentDailyTask 开始 ByCorn：" + LocalDateTime.now());
        List<Phone> phoneList = phoneServiceImpl.selectPhoneListByExample(new PhoneExample());
        for (Phone phone : phoneList) {
            analysisServiceImpl.analyseSentiment(phone.getId());
        }
        logger.info("analyseSentimentDailyTask 结束 ByCorn：" + LocalDateTime.now());
    }

    //    @Test
//    void analyseTopicTest() {
//        LocalDateTime end = LocalDateTime.now();
//        LocalDateTime start = end.toLocalDate().minusDays( 7 ).atStartOfDay();
//        System.out.println( analysisServiceImpl.analyseTopic( start, end ) );
//    }

}
