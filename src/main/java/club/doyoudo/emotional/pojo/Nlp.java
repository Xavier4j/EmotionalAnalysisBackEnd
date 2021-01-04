package club.doyoudo.emotional.pojo;

import com.baidu.aip.nlp.AipNlp;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@Component //将该Util作为单例使用
@PropertySource({"classpath:config/nlp.properties"})
public class Nlp {
    AipNlp client;

    public Nlp(@Value( "${nlp.APP_ID}" ) String APP_ID, @Value( "${nlp.API_KEY}" ) String API_KEY, @Value( "${nlp.SECRET_KEY}" ) String SECRET_KEY) {
        // 初始化一个AipNlp
//        System.out.println(APP_ID+API_KEY+SECRET_KEY);
        this.client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public AipNlp getClient() {
        return client;
    }
}
