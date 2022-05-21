package com.management.cni.security.mapper;

import com.management.cni.entity.Invitation;
import com.management.cni.security.dto.request.InvitationRequest;
import com.management.cni.security.dto.response.InvitationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvitationMapper {

  InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);

  InvitationResponse convertToInvitationResponse(Invitation invitation);

  Invitation convertToInvitation(InvitationRequest invitationRequest);

}
