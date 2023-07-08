package com.example.L07springbootanotations;


import org.gfg.analyzer.KeywordAnalyzerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${company.name}")
    private String companyName;

    @Autowired
    private ProductService productService;

    @Autowired
//    @Qualifier("keywordAnalyzerImpl")
    private KeywordAnalyzerInterface keywordAnalyzerInterface;

//    public ProductController(ProductService productService, KeywordAnalyzerInterface keywordAnalyzerInterface) {
//        this.productService = productService;
//        this.keywordAnalyzerInterface = keywordAnalyzerInterface;
//    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<String>> getAllKeywords(@RequestParam String keyword){
        keywordAnalyzerInterface.recordKeyword(keyword);
        return ResponseEntity.ok(keywordAnalyzerInterface.getAllKeyword());
    }


    @GetMapping("/getName")
    public ResponseEntity<String> getCompanyName(){
        return ResponseEntity.ok(companyName);
    }



}
