package com.stockmanagement.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.stockmanagement.model.ExportImportModel;
import com.stockmanagement.service.base.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StatisticController {

    private final ProductService productService;

    @GetMapping("/statistic/import")
    public String getHistoryImport(Model model) {
        model.addAttribute("imports", productService.getHistoryImport());
        return "import_products";
    }

    @GetMapping("/statistic/export")
    public String getHistoryExport(Model model) {
        model.addAttribute("exports", productService.getHistoryExport());
        return "export_products";
    }

    @GetMapping(value = "/statistic/import/search")
    public String searchProductImport(HttpServletResponse response, @RequestParam("product_name") String productName, @RequestParam( "product_id") Integer productId,
                                      @RequestParam("employee_name") String emplyeeName, @RequestParam("employee_id") Integer employeeId,
                                      @RequestParam("date") String date, @RequestParam("number") Integer number, Model model) {
        List<ExportImportModel> searchResult = productService.importProductSearch(number, date, emplyeeName, productName, productId, employeeId);
        model.addAttribute("imports", searchResult);
        return "import_products";
    }

    @GetMapping(value = "/statistic/import/search", params="action=CSV")
    public void exportCSVImport(HttpServletResponse response, @RequestParam("product_name") String productName, @RequestParam( "product_id") Integer productId,
                                @RequestParam("employee_name") String emplyeeName, @RequestParam("employee_id") Integer employeeId,
                                @RequestParam("date") String date, @RequestParam("number") Integer number) throws Exception {
        //set file name and content type
        String filename = "imports.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportImportModel> writer = new StatefulBeanToCsvBuilder<ExportImportModel>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write( productService.importProductSearch(number, date, emplyeeName, productName, productId, employeeId));

    }

    @GetMapping(value = "/statistic/export/search")
    public String searchProductExport(HttpServletResponse response, @RequestParam("product_name") String productName, @RequestParam( "product_id") Integer productId,
                                      @RequestParam("employee_name") String emplyeeName, @RequestParam("employee_id") Integer employeeId,
                                      @RequestParam("date") String date, @RequestParam("number") Integer number, Model model) {
        List<ExportImportModel> searchResult = productService.exportProductSearch(number, date, emplyeeName, productName, productId, employeeId);
        model.addAttribute("exports", searchResult);
        return "export_products";
    }

    @GetMapping(value = "/statistic/export/search", params="action=CSV")
    public void exportCSVExport(HttpServletResponse response, @RequestParam("product_name") String productName, @RequestParam( "product_id") Integer productId,
                                @RequestParam("employee_name") String emplyeeName, @RequestParam("employee_id") Integer employeeId,
                                @RequestParam("date") String date, @RequestParam("number") Integer number) throws Exception {
        //set file name and content type
        String filename = "exports.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExportImportModel> writer = new StatefulBeanToCsvBuilder<ExportImportModel>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write( productService.exportProductSearch(number, date, emplyeeName, productName, productId, employeeId));

    }
}
