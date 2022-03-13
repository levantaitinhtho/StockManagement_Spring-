package com.stockmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_product")
    @NotEmpty
    private String nameProduct;

    @Column(name = "producer")
    private String producer;

    @Column(name = "import_date")
    private String importDate;

    @Column(name = "number")
    @NotNull
    private Integer number;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "active")
    private Integer active;

}
