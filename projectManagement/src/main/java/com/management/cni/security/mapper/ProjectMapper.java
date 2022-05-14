package com.management.cni.security.mapper;

import com.management.cni.Entity.Project;
import com.management.cni.security.dto.request.ProjectRequest;
import com.management.cni.security.dto.response.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

  ProjectResponse convertToProjectResponse(Project project);

  Project convertToProject(ProjectRequest projectRequest);

}
