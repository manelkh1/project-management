package com.management.cni.security.mapper;

import com.management.cni.Entity.Admin;
import com.management.cni.security.dto.request.AdminRequest;
import com.management.cni.security.dto.response.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

  AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

  AdminResponse convertToAdminResponse(Admin admin);

  Admin convertToAdmin(AdminRequest adminRequest);

}
