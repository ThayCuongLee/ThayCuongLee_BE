package com.huynhducphu.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Admin 6/14/2026
 *
 **/
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiPageResponse<T> {

    List<T> data;

    int page;
    int size;
    long totalElements;
    int totalPages;

    public ApiPageResponse(Page<T> page) {
        this.data = page.stream().toList();
        this.page = page.getNumber() + 1;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
