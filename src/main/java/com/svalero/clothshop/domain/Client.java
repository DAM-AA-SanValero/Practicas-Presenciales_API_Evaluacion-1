package com.svalero.clothshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = ":This field can't be blank")
    @NotNull(message = ":This field is required")
    private String name;

    @Column
    @NotBlank(message = ":This field can't be empty")
    @NotNull(message = ":This field is required")
    private String address;

    @Column
    @PositiveOrZero(message = ":This field can only contain positive numbers or zero")
    private int account;

    @OneToMany(mappedBy = "id")
    @JsonBackReference(value = "user-product")
    private List<Product> products;

}
