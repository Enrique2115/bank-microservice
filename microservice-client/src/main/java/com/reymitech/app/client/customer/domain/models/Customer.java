package com.reymitech.app.client.customer.domain.models;

import com.reymitech.app.client.customer.domain.enums.Active;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Representa una clase Cliente que extiende BaseEntity.
 *
 * Esta clase contiene columnas para nombre de usuario, contraseña, correo electrónico, detalles del cliente y tipo de cliente.
 * La columna nombre de usuario es única y no puede ser nula.
 * La columna de contraseña no puede ser nula.
 * La columna email es única, no puede ser nula y no es actualizable.
 * La columna de detalles del cliente está incrustada.
 * La columna de tipo cliente es una relación muchos-a-uno con TypeCustomer, con una columna de unión no nula llamada «type_customer_id».
 */
@Entity(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Embedded
    private CustomerDetails customerDetails;

    @ManyToOne
    @JoinColumn(name = "type_customer_id", nullable = false)
    private TypeCustomer typeCustomer;

    @Enumerated(EnumType.STRING)
    private Active active;
}
