package com.example.dayone.service;

import com.example.dayone.exception.impl.NoCompanyException;
import com.example.dayone.model.Company;
import com.example.dayone.model.Dividend;
import com.example.dayone.model.ScrapedResult;
import com.example.dayone.model.constants.CacheKey;
import com.example.dayone.persist.CompanyRepository;
import com.example.dayone.persist.DividendRepository;
import com.example.dayone.persist.entity.CompanyEntity;
import com.example.dayone.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
    public ScrapedResult getDividendByCompanyName(String companyName) {
        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity companyEntity = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new NoCompanyException());

        // 2. 조회된 회사 ID 로 배당금 정보 조회
        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(companyEntity.getId());

        return new ScrapedResult(
                Company.from(companyEntity)
                , dividendEntities.stream()
                .map(Dividend::from)
                .collect(Collectors.toList())
        );
    }
}
