package club.doyoudo.emotional.utils;

import club.doyoudo.emotional.model.Comment;
import club.doyoudo.emotional.model.Phone;
import club.doyoudo.emotional.service.CommentService;
import club.doyoudo.emotional.service.PhoneService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Component
@Data
public class ImportComment {

    PhoneService phoneServiceImpl;
    CommentService commentServiceImpl;

    @Autowired(required = false)
    public ImportComment(PhoneService phoneServiceImpl, CommentService commentServiceImpl) {
        this.phoneServiceImpl = phoneServiceImpl;
        this.commentServiceImpl = commentServiceImpl;
    }

    private StringBuilder replaceAll;//初始化
    private String encoding = "UTF-8";
    private int size = 500;
    private String[] fileNames = {"HUAWEI Mate40.txt", "HUAWEI P40.txt", "iPhone 11.txt", "OPPO Reno5.txt", "VIVO IQOO Z1.txt", "VIVO NEX3.txt", "XIAOMI 10.txt"};

    public void runImportComment() {
        for (String fileName:fileNames) {
            String model = fileName.split(".")[0];
            Phone phone = new Phone();
            phone.setModel(model);
            String phoneId = (String) phoneServiceImpl.insertPhone(phone).getData();

            //加载评论
            InputStreamReader read = null;
            BufferedReader bufferedReader = null;
            try {
                read = new InputStreamReader(Objects.requireNonNull(ImportComment.class.getClassLoader().getResourceAsStream("comments/"+fileName)), encoding);
                bufferedReader = new BufferedReader(read);
                for (String txt = null; (txt = bufferedReader.readLine()) != null; ) {
                    Comment comment = new Comment();
                    comment.setContent(txt);
                    comment.setPhoneId(phoneId);
                    commentServiceImpl.insertComment(comment);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bufferedReader)
                        bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null != read)
                        read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
