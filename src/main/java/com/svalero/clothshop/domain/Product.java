package com.svalero.clothshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull(message = "<-- Este campo es obligatorio")
    @Positive(message = "<-- Este campo solo puede contener números positivos y es obligatorio")
    private float price;

    @Column
    @NotNull(message = "<-- Este campo es obligatorio")
    @Positive(message = "<-- Este campo solo puede contener números positivos y es obligatorio")
    private float size;
    @Column
    private boolean available;


}
