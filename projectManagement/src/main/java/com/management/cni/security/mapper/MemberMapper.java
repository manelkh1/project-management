package com.management.cni.security.mapper;

import com.management.cni.Entity.Member;
import com.management.cni.security.dto.request.MemberRequest;
import com.management.cni.security.dto.response.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  MemberResponse convertToMemberResponse(Member member);

  Member convertToMember(MemberRequest memberRequest);

}
