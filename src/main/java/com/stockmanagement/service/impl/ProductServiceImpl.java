package com.stockmanagement.service.impl;

import com.stockmanagement.entity.ExportProduct;
import com.stockmanagement.entity.ImportProduct;
import com.stockmanagement.entity.Product;
import com.stockmanagement.model.ExportImportModel;
import com.stockmanagement.model.Transfer;
import com.stockmanagement.repository.ExportProductRepository;
import com.stockmanagement.repository.ImportProductRepository;
import com.stockmanagement.repository.ProductRepository;
import com.stockmanagement.service.base.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImportProductRepository importProductRepository;
    private final ExportProductRepository exportProductRepository;

    @Override
    public List<Product> getAllProduct() {
        List<Product> listProducts = productRepository.findAll();
        List<Product> actives = getActiveProducts(listProducts);
        return actives;
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void addProduct(Product product) {
        product.setActive(1);
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Integer id) {
        Product p = productRepository.findById(id).get();
        p.setActive(0);
        productRepository.save(p);
    }

    @Override
    public List<Product> searchProducts(String term) {
        List<Product> listProducts = productRepository.findByNameContaining(term);
        List<Product> actives = getActiveProducts(listProducts);
        return actives;
    }

    @Override
    public void importProducts(ImportProduct importProduct) {
        importProductRepository.save(importProduct);
        Product product = productRepository.findById(importProduct.getProduct().getId()).get();
        Integer number = product.getNumber() + importProduct.getNumber();
        productRepository.updateNumberProduct(product.getId(), number);
    }

    @Override
    public void exportProducts(ExportProduct exportProduct) {
        exportProductRepository.save(exportProduct);
        Product product = productRepository.findById(exportProduct.getProduct().getId()).get();
        Integer number = product.getNumber() - exportProduct.getNumber();
        productRepository.updateNumberProduct(product.getId(), number);
    }

    @Override
    public List<ExportImportModel> getHistoryImport() {
        List<ImportProduct> list = importProductRepository.findAll();
        System.out.println("list import size: " + list.size());
        List<ExportImportModel> responseList = new ArrayList<>();
        for (ImportProduct imp : list) {
            ExportImportModel entity = Transfer.importProductToExportImportModel(imp);
            System.out.println(entity.toString());
            responseList.add(entity);
        }
        Collections.reverse(responseList);
        return responseList;
    }
    @Override
    public List<ExportImportModel> getHistoryExport() {
        List<ExportProduct> list = exportProductRepository.findAll();
        List<ExportImportModel> responseList = new ArrayList<>();
        for (ExportProduct exp : list) {
            ExportImportModel entity = Transfer.exportProductToExportImportModel(exp);
            responseList.add(entity);
        }
        Collections.reverse(responseList);
        return responseList;
    }
    private List<Product> getActiveProducts(List<Product> listProducts) {
        List<Product> actives = new ArrayList<>();
        if (listProducts == null || listProducts.isEmpty()) {
            return actives;
        }
        for (Product p : listProducts) {
            if (p.getActive() == 1) {
                actives.add(p);
            }
        }
        return actives;
    }

    @Override
    public List<ExportImportModel> importProductSearch(Integer numberSearch, String dateSearch, String userNameSearch,
                                            String nameProductSearch, Integer productIdSearch, Integer employeeIdSearch) {
        List<Object[]> seachResult = importProductRepository.searchImportProduct(numberSearch, dateSearch, userNameSearch,
                nameProductSearch, productIdSearch, employeeIdSearch);
        List<ExportImportModel> results = new ArrayList<>();
        for (Object[] o : seachResult) {
            ExportImportModel entity = Transfer.objectToExportImportModel(o);
            results.add(entity);
        }
        return results;
    }

    @Override
    public List<ExportImportModel> exportProductSearch(Integer numberSearch, String dateSearch, String userNameSearch,
                                            String nameProductSearch, Integer productIdSearch, Integer employeeIdSearch) {
        List<Object[]> seachResult = exportProductRepository.searchExportProduct(numberSearch, dateSearch, userNameSearch,
                nameProductSearch, productIdSearch, employeeIdSearch);
        List<ExportImportModel> results = new ArrayList<>();
        for (Object[] o : seachResult) {
            ExportImportModel entity = Transfer.objectToExportImportModel(o);
            results.add(entity);
        }
        return results;
    }
}
