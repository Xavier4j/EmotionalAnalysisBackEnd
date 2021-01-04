package club.doyoudo.emotional.pojo;

import club.doyoudo.emotional.model.CommentTag;
import club.doyoudo.emotional.model.Phone;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PhoneWithAnalysis extends Phone {
    Double sentimentAverage;
    Double positiveProbAverage;
    Double positiveCommentNum;
    Double neutralCommentNum;
    Double negativeCommentNum;
    List<CommentTag> commentTagList;
}
