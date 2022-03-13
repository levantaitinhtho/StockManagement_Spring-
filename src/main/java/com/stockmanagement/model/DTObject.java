package com.stockmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTObject {
    private Integer productId;
    private String productName;
    private Integer employeeId;
    private String emploeeName;
    private Integer number;
    private String date;
}
