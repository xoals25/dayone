package com.example.dayone.model;

import com.example.dayone.persist.entity.DividendEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dividend {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    private String dividend;

    public static Dividend from(DividendEntity entity) {
        return Dividend.builder()
                .date(entity.getDate())
                .dividend(entity.getDividend())
                .build();
    }

    public static Dividend of(LocalDateTime date, String dividend) {
        return Dividend.builder()
                .date(date)
                .dividend(dividend)
                .build();
    }
}
