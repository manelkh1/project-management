package com.management.cni.security.mapper;

import com.management.cni.Entity.Manager;
import com.management.cni.security.dto.request.ManagerRequest;
import com.management.cni.security.dto.response.ManagerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerMapper {

  ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);

  ManagerResponse convertToManagerResponse(Manager manager);

  Manager convertToManager(ManagerRequest managerRequest);

}
