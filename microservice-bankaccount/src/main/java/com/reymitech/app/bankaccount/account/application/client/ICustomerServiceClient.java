package com.reymitech.app.bankaccount.account.application.client;

import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-client")
public interface ICustomerServiceClient {
    @GetMapping("/api/v1/customer/document")
    CustomerDto getCustomerByDocument(@RequestParam final String document);

}
