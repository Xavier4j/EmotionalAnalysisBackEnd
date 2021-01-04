package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.CommentMapper;
import club.doyoudo.emotional.model.Comment;
import club.doyoudo.emotional.model.Phone;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.service.CommentService;
import club.doyoudo.emotional.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;
    private PhoneService phoneServiceImpl;

    @Autowired(required = false)
    public CommentServiceImpl(CommentMapper commentMapper, PhoneService phoneServiceImpl) {
        this.commentMapper = commentMapper;
        this.phoneServiceImpl = phoneServiceImpl;
    }

    @Override
    public ResponseWrapper insertComment(Comment comment) {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        comment.setId(uuid);
        LocalDateTime time = LocalDateTime.now();
        comment.setCreateTime(time);

        int i = commentMapper.insertSelective(comment);
        phoneServiceImpl.updatePhoneCommentNum(comment.getPhoneId(), true);

        if (i == 1) {
            Phone phone = phoneServiceImpl.selectPhoneById1(comment.getPhoneId());
            return new ResponseWrapper(true, 200, "成功！", null);
        } else {
            return new ResponseWrapper(false, 500, "失败，请稍后重试！", null);
        }
    }

    @Override
    public ResponseWrapper deleteComment(String commentId) {
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setIsDelete(true);
        int i = commentMapper.updateByPrimaryKeySelective(comment);
        phoneServiceImpl.updatePhoneCommentNum(commentMapper.selectByPrimaryKey(commentId).getPhoneId(), false);
        if (i == 1) {
            return new ResponseWrapper(true, 200, "成功！", null);
        } else {
            return new ResponseWrapper(false, 500, "失败，请稍后重试！", null);
        }
    }
}
