package com.svalero.clothshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "<-- Este campo no puede estar vacio")
    @NotNull(message = "<-- Este campo es obligatorio")
    private String name;

    @Column
    @NotBlank(message = "<-- Este campo no puede estar vacio")
    @NotNull(message = "<-- Este campo es obligatorio")
    private String address;

    @Column
    @PositiveOrZero(message = "<-- Este campo solo puede contener números positivos y 0")
    private int account;

}
