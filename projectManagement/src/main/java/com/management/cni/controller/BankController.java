package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.BankRequest;
import com.management.cni.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/banks")
public class BankController {

  @Autowired
  private BankService bankService;
/*
  @GetMapping("/banks")
  public ResponseEntity<ApiResponse> getAllBanks() {
    ApiResponse apiResponse = bankService.getAllBanks();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }*/

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getBankById(@PathVariable("id") long id) {
    ApiResponse apiResponse = bankService.getBankById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("")
  public ResponseEntity<ApiResponse> createBank(@RequestBody BankRequest bankRequest) {
    ApiResponse apiResponse = bankService.createBank(bankRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateBank(@PathVariable("id") long id, @RequestBody BankRequest bankRequest) {
    ApiResponse apiResponse = bankService.updateBank(id, bankRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteBankById(@PathVariable("id") long id) {
    ApiResponse apiResponse = bankService.deleteBankById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}
