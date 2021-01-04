package club.doyoudo.emotional.service;

import club.doyoudo.emotional.model.Comment;
import club.doyoudo.emotional.pojo.ResponseWrapper;

public interface CommentService {

    ResponseWrapper insertComment(Comment comment);

    ResponseWrapper deleteComment(String commentId);
}
