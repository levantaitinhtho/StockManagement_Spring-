package com.stockmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportImportModel {
    private Integer productId;

    private String productName;

    private Integer userId;

    private String userName;

    @NotEmpty
    private String date;

    @NotNull
    private Integer number;

    private Double price;

    public ExportImportModel(@NotNull Integer productId, String productName, String date, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.date = date;
        this.price = price;
    }

}
