package com.reymitech.app.client.customer.infraestructure.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CustomerRequest {

    @NotBlank(message = "El nombre no debe estar vacío")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String name;

    @NotBlank(message = "El apellido no debe estar vacío")
    @Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
    private String lastname;

    @NotBlank(message = "El documento no debe estar vacío")
    @Size(min = 3, max = 15, message = "El documento debe tener entre 3 y 15 caracteres")
    private String document;

    @NotBlank(message = "El email no debe estar vacío")
    @Size(min = 3, max = 50, message = "El email debe tener entre 3 y 50 caracteres")
    private String email;

    @NotBlank(message = "El password no debe estar vacío")
    @Size(min = 3, max = 20, message = "El password debe tener entre 3 y 20 caracteres")
    private String username;

    @NotBlank(message = "El password no debe estar vacío")
    @Size(min = 3, max = 20, message = "El password debe tener entre 3 y 20 caracteres")
    private String password;

    @NotBlank(message = "El telefono no debe estar vacío")
    @Size(max = 9, message = "El telefono debe tener entre 0 y 9 caracteres")
    private String phone;

    @NotBlank(message = "El correo electrónico no debe estar vacío")
    @Size(min = 3, max = 50, message = "El correo electrónico debe tener entre 3 y 50 caracteres")
    private String address;

    @NotBlank(message = "El tipo de cliente no debe estar vacío")
    @Size(min = 3, max = 36, message = "El tipo de cliente debe tener entre 3 y 36 caracteres")
    private String typeCustomer;

}
