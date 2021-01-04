package club.doyoudo.emotional.pojo;

import club.doyoudo.emotional.model.AnalysisSentiment;
import club.doyoudo.emotional.model.Phone;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnalysisSentimentWithPhone extends AnalysisSentiment {
    private Phone phone;
}
