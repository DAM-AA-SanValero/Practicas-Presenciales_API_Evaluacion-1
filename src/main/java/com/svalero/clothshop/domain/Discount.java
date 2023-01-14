package com.svalero.clothshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = ":This field can't be empty")
    @NotNull(message = ":This field is required")
    private String event;
    @Column
    private LocalDate discountDate;

    @Column
    @NotNull(message = ":This field is required")
    @Positive(message = ":This field can only contain positive numbers")
    private float discountedPrice;

    @OneToMany(mappedBy = "id")
    @JsonBackReference(value = "dicount-product")
    private List<Product> products;

}
