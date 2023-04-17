package com.example.dayone.web;

import com.example.dayone.model.ScrapedResult;
import com.example.dayone.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<ScrapedResult> searchFinance(@PathVariable String companyName) {
        return ResponseEntity.ok(
                this.financeService.getDividendByCompanyName(companyName)
        );
    }

}
