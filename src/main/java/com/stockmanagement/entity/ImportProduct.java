package com.stockmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "import_product")
public class ImportProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    @NotNull
    private Integer number;

    @Column(name = "date_update")
    @NotNull
    private String dateUpdate;

    @Column(name = "price")
    @NotNull
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ImportProduct(Integer number, String dateUpdate, Employee employee, Product product, Double price) {
        this.number = number;
        this.dateUpdate = dateUpdate;
        this.employee = employee;
        this.product = product;
        this.price = price;
    }
}
