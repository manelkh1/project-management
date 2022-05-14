package com.management.cni.security.mapper;

import com.management.cni.Entity.Bank;
import com.management.cni.security.dto.request.BankRequest;
import com.management.cni.security.dto.response.BankResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {

  BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

  BankResponse convertToBankResponse(Bank bank);

  Bank convertToBank(BankRequest bankRequest);

}
