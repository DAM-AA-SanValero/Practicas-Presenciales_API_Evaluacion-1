package com.svalero.clothshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "<-- Este campo no puede estar vacio")
    @NotNull(message = "<-- Este campo es obligatorio")
    private String event;
    @Column
    private LocalDate discountDate;

    @Column
    @NotNull(message = "<-- Este campo es obligatorio")
    @Positive(message = "<-- Este campo solo puede contener números positivos y es obligatorio")
    private float discountedPrice;
}
