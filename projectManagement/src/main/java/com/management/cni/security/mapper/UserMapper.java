package com.management.cni.security.mapper;

import com.management.cni.entity.User;
import com.management.cni.security.dto.request.UserRequest;
import com.management.cni.security.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserResponse convertToUserResponse(User user);

  User convertToUser(UserRequest userRequest);

}
