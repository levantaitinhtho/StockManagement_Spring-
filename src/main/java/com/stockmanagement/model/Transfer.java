package com.stockmanagement.model;

import com.stockmanagement.entity.Employee;
import com.stockmanagement.entity.ExportProduct;
import com.stockmanagement.entity.ImportProduct;
import com.stockmanagement.entity.Product;

public class Transfer {
    public static ExportImportModel importProductToExportImportModel(ImportProduct imp) {
        Product p = imp.getProduct();
        Employee e = imp.getEmployee();
        ExportImportModel ExportImportModel = new ExportImportModel(p.getId(), p.getNameProduct(), e.getId(), e.getUserName(), imp.getDateUpdate(), imp.getNumber(), imp.getPrice());
        return ExportImportModel;
    }

    public static ImportProduct exportImportModelToImportProduct(ExportImportModel exportImportModel, Product p, Employee e) {
        ImportProduct imp = new ImportProduct(exportImportModel.getNumber(), exportImportModel.getDate(),e, p, exportImportModel.getPrice());
        return imp;
    }

    public static ExportImportModel exportProductToExportImportModel(ExportProduct exp) {
        Product p = exp.getProduct();
        Employee e = exp.getEmployee();
        ExportImportModel ExportImportModel = new ExportImportModel(p.getId(), p.getNameProduct(), e.getId(), e.getUserName(), exp.getDateExport(), exp.getNumber(), exp.getPrice());
        return ExportImportModel;
    }

    public static ExportProduct exportImportModelToExportProduct(ExportImportModel exportImportModel, Product p, Employee e) {
        ExportProduct exp = new ExportProduct(exportImportModel.getNumber(), exportImportModel.getDate(),e, p, exportImportModel.getPrice());
        return exp;
    }

    public static ExportImportModel objectToExportImportModel(Object[] o) {
        Integer productId = Integer.valueOf(o[0].toString());
        String productName = o[1].toString();
        Integer userId = Integer.valueOf(o[2].toString());
        String userName = o[3].toString();
        Integer number = Integer.valueOf(o[4].toString());
        String date  = o[5].toString();
        Double price = Double.valueOf(o[6].toString());
        ExportImportModel exportImportModel = new ExportImportModel(productId, productName, userId, userName, date, number, price);
        return exportImportModel;
    }
}
