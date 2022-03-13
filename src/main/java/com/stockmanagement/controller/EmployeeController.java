package com.stockmanagement.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import com.stockmanagement.entity.Employee;
import com.stockmanagement.entity.ExportProduct;
import com.stockmanagement.entity.ImportProduct;
import com.stockmanagement.entity.Product;
import com.stockmanagement.model.ExportImportModel;
import com.stockmanagement.model.Transfer;
import com.stockmanagement.service.base.EmployeeService;
import com.stockmanagement.service.base.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final ProductService productService;
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public String user() {
        return "employee";
    }

    @GetMapping("/employee/products")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "employee_list_product";
    }

    @GetMapping("/employee/product/search")
    public String searchProduct(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/employee/products";
        }
        model.addAttribute("products", productService.searchProducts(term));
        return "employee_list_product";
    }


    @GetMapping("/product/{id}/import")
    public String importProduct(@PathVariable("id") Integer id, Model model) {
        System.out.println("productId: " + id);
        Product p = productService.getProductById(id);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = date.format(formatter);
        ExportImportModel importP = new ExportImportModel(id, p.getNameProduct(), dateString, p.getPrice());
        model.addAttribute("entity", importP);
        return "import_form";
    }

    @PostMapping("/product/import_new")
    public String importNew(@Valid ExportImportModel entity, BindingResult result, RedirectAttributes redirect, Principal principal) {
        if (result.hasErrors()) {
            return "import_form";
        }
        System.out.println("Entity to import: " + entity.toString());
        Product p = productService.getProductById(entity.getProductId());
        System.out.println("pricipal.getName = " + principal.getName());
        Employee e = employeeService.findByName(principal.getName());
        entity.setPrice(p.getPrice()*entity.getNumber());
        ImportProduct imp = Transfer.exportImportModelToImportProduct(entity, p, e);
        productService.importProducts(imp);
        redirect.addFlashAttribute("successMessage", "Import successfully!");
        return "redirect:/statistic/import";
    }

    @GetMapping("/product/{id}/export")
    public String exportProduct(@PathVariable("id") Integer id, Model model) {
        System.out.println("productId: " + id);
        Product p = productService.getProductById(id);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = date.format(formatter);
        ExportImportModel exportP = new ExportImportModel(id, p.getNameProduct(), dateString, p.getPrice());
        model.addAttribute("entity", exportP);
        return "export_form";
    }

    @PostMapping("/product/export_new")
    public String exportNew(@Valid ExportImportModel entity, BindingResult result, RedirectAttributes redirect, Principal principal) {
        if (result.hasErrors()) {
            return "export_form";
        }
        System.out.println("Entity to export: " + entity.toString());
        Product p = productService.getProductById(entity.getProductId());
        System.out.println("pricipal.getName = " + principal.getName());
        Employee e = employeeService.findByName(principal.getName());
        entity.setPrice(p.getPrice()*entity.getNumber());
        ExportProduct exp = Transfer.exportImportModelToExportProduct(entity, p, e);
        productService.exportProducts(exp);
        redirect.addFlashAttribute("successMessage", "Export successfully!");
        return "redirect:/statistic/export";
    }
}
