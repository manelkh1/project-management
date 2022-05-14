package com.management.cni.security.mapper;

import com.management.cni.Entity.Attachment;
import com.management.cni.security.dto.request.AttachmentRequest;
import com.management.cni.security.dto.response.AttachmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttachementMapper {

  AttachementMapper INSTANCE = Mappers.getMapper(AttachementMapper.class);

  AttachmentResponse convertToAttachmentResponse(Attachment Attachment);

  Attachment convertToAttachment(AttachmentRequest AttachmentRequest);

}
