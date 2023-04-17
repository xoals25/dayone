package com.example.dayone.scraper;


import com.example.dayone.model.Company;
import com.example.dayone.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
