package com.reymitech.app.client.typecustomer.domain.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO used to create a new customer type.
 */
@Data
public class CreateCustomerType {
    @NotBlank(message = "El nombre no debe estar vacío")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String name;
}
