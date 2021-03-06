package com.management.cni.service;

import com.management.cni.entity.Bank;
import com.management.cni.entity.User;
import com.management.cni.repository.BankRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.BankRequest;
import com.management.cni.security.dto.response.BankResponse;
import com.management.cni.security.mapper.BankMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class BankService {

  @Autowired
  BankRepository bankRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private SessionService sessionService;


/*  public ApiResponse getAllBanks() {
    User user = userService.getConnectedAdmin();
    List<BankResponse> bankResponses = new ArrayList<>();
    try {

      if (user != null) {
        List<Bank> banks = bankRepository.findAll();
        if (!banks.isEmpty()) {
          for (Bank bank : banks) {
            BankResponse bankResponse = BankMapper.INSTANCE.convertToBankResponse(bank);
            bankResponses.add(bankResponse);
          }
        }
        return new ApiResponse(bankResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

    }
  }*/

  public ApiResponse getBankById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user.getAdmin() != null || user.getMember() != null || user.getBank() != null) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
          BankResponse bankResponse = BankMapper.INSTANCE.convertToBankResponse(bank.get());
          return new ApiResponse(bankResponse, null, HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "BANK DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse createBank(BankRequest bankRequest) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Bank bank = BankMapper.INSTANCE.convertToBank(bankRequest);
        ///convert userRequest to USER PERSISTENCE SO YOU CAN STORE IT IN DATABASE

        String password = passwordEncoder.encode(bankRequest.getUserRequest().getPassword());
        bank.getUser().setPassword(password);
        bank.getUser().setStatus(false);
        String token = UUID.randomUUID().toString();//generate token randomly
        sessionService.saveSession(bank.getUser(), token);//put a token and a saveduser in a session
        // emailService.sendHtmlMail(bank.getUser());
        bankRepository.save(bank);
        return new ApiResponse(null, "BANK CREATED", HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse deleteBankById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
          bankRepository.deleteById(id);
          return new ApiResponse(null, "BANK DELETED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "BANK DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse updateBank(Long id, BankRequest bankRequest) {
    User user = userService.getConnectedBank();
    try {
      if (user != null) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
          User newUser = bank.get().getUser();

          newUser.getBank().setBankName(bankRequest.getBankName());
          user.getBank().setUser(newUser);
          bankRepository.save(user.getBank());
          return new ApiResponse(null, "BANK UPDATED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "BANK DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

}
