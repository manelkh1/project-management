package com.management.cni.security.mapper;

import com.management.cni.Entity.Comment;
import com.management.cni.security.dto.request.CommentRequest;
import com.management.cni.security.dto.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  CommentResponse convertToCommentResponse(Comment comment);

  Comment convertToComment(CommentRequest commentRequest);

}
