package com.reymitech.app.client.customer.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDetails {

    /** Column for the first name. */
    private String firstName;

    /** Column for the last name. */
    private String lastName;

    /** Column for the phone. */
    private String phone;

    /** Column for the address. */
    private String address;

    private String document;
}
