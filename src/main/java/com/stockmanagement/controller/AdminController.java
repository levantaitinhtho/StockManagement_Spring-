package com.stockmanagement.controller;

import com.stockmanagement.entity.Employee;
import com.stockmanagement.entity.Product;
import com.stockmanagement.service.base.EmployeeService;
import com.stockmanagement.service.base.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final EmployeeService employeeService;

    @GetMapping("/admins")
    public String getAdmin() {
        return "admin.html";
    }
    @GetMapping("")
    public String getInventory() {
        return "home.html";
    }
    @GetMapping("user")
    public String getUser() {
        return "user.html";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/home")
    public String getHome(){
        return "home.html";
    }

    @GetMapping("/admin/products")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "admin_list_product.html";
    }
    @GetMapping("/logout")
    public String logout(){
        return "home.html";
    }

    @GetMapping("/admin/product/search")
    public String searchProduct(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin/products";
        }
        model.addAttribute("products", productService.searchProducts(term));
        return "admin_list_product";
    }

    @GetMapping("/admin/product/add")
    public String add(Model model) {
        Product product = new Product();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = date.format(formatter);
        product.setImportDate(dateString);
        model.addAttribute("product", product);
        return "admin_form_product.html";
    }

    @GetMapping("/admin/product/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        System.out.println("id: " + id);
        model.addAttribute("product", productService.getProductById(id));
        return "admin_form_product";
    }

    @PostMapping("/admin/product/save")
    public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin_form_product";
        }
        productService.addProduct(product);
        redirect.addFlashAttribute("successMessage", "Saved product successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        System.out.println("id: " + id);
        productService.deleteProduct(id);
        redirect.addFlashAttribute("successMessage", "Deleted product successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/emp")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin_list_employees";
    }

    @GetMapping("/admin/emp/search")
    public String searchEmployee(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin/emp";
        }
        model.addAttribute("employees", employeeService.searchEmployees(term));
        return "admin_list_employees";
    }

    @GetMapping("/admin/emp/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin_form_employee";
    }
    @PostMapping("/admin/emp/save")
    public String addNewUser(@Valid Employee employee, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin_form_employee";
        }
        employeeService.addEmployee(employee);
        redirect.addFlashAttribute("successMessage", "Saved employee successfully!");
        return "redirect:/admin/emp";
    }

    @GetMapping("/admin/emp/{id}/deactive")
    public String deActiveEmployee(@PathVariable int id, RedirectAttributes redirect) {
        System.out.println("id: " + id);
        employeeService.deactiveEmployee(id);
        redirect.addFlashAttribute("successMessage", "Deactivate employee successfully!");
        return "redirect:/admin/emp";
    }

}
