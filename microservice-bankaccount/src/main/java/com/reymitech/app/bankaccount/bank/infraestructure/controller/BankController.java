package com.reymitech.app.bankaccount.bank.infraestructure.controller;

import com.reymitech.app.bankaccount.bank.application.services.IBankService;
import com.reymitech.app.bankaccount.bank.domain.dtos.BankDTO;
import com.reymitech.app.bankaccount.bank.infraestructure.request.CreateBankRequest;
import com.reymitech.app.bankaccount.bank.infraestructure.request.UpdateBankRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
public class BankController {

    private final IBankService bankService;
    private final ModelMapper modelMapper;

    @RequestMapping("/get")
    public ResponseEntity<BankDTO> getBankById() {
        return ResponseEntity.ok(modelMapper.map(bankService.getBank(), BankDTO.class));
    }

    @RequestMapping("/create")
    public ResponseEntity<BankDTO> createBank(@RequestBody CreateBankRequest bankDTO) {
        return ResponseEntity.ok(modelMapper.map(bankService.createBank(bankDTO), BankDTO.class));
    }

    @RequestMapping("/update")
    public ResponseEntity<BankDTO> updateBank(@RequestBody UpdateBankRequest bankDTO) {
        bankService.updateBank(bankDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/delete")
    public ResponseEntity<Void> deleteBank() {
        bankService.deleteBank();
        return ResponseEntity.ok().build();
    }
}
