package com.stockmanagement.service.base;

import com.stockmanagement.entity.ExportProduct;
import com.stockmanagement.entity.ImportProduct;
import com.stockmanagement.entity.Product;
import com.stockmanagement.model.ExportImportModel;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();
    Product getProductById(Integer id);
    void addProduct(Product product);
    void deleteProduct(Integer id);

    void importProducts(ImportProduct importProduct);
    void exportProducts(ExportProduct exportProduct);
    List<Product> searchProducts(String term);
    List<ExportImportModel> getHistoryImport();
    List<ExportImportModel> getHistoryExport();
    List<ExportImportModel> importProductSearch(Integer numberSearch, String dateSearch, String userNameSearch,
                                     String nameProductSearch, Integer productIdSearch, Integer employeeIdSearch);
    List<ExportImportModel> exportProductSearch(Integer numberSearch, String dateSearch, String userNameSearch,
                                     String nameProductSearch, Integer productIdSearch, Integer employeeIdSearch);
}
