package com.svalero.clothshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @NotNull(message = ":This field is required")
    @Positive(message = ":This field can only contain positive numbers")
    private float price;


    @Column
    @NotNull(message = ":This field is required")
    @Positive(message = ":This field can only contain positive numbers")
    private float size;
    @Column
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "user-product")
    private Client client_id;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount_id;


}
