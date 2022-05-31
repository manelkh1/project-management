package com.management.cni.security.mapper;

import com.management.cni.entity.Admin;
import com.management.cni.security.dto.request.AdminRequest;
import com.management.cni.security.dto.response.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

  AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
  /// convertit response dto en une entité
  AdminResponse convertToAdminResponse(Admin admin);
  /// convertir admin entity en un dto request
  Admin convertToAdmin(AdminRequest adminRequest);

}
