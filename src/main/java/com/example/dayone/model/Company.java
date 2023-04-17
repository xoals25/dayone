package com.example.dayone.model;

import com.example.dayone.persist.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    private String ticker;
    private String name;

    public static Company from(CompanyEntity entity) {
        return Company.builder()
                .ticker(entity.getTicker())
                .name(entity.getName())
                .build();
    }
}
