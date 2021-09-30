package com.houyu.online_learning_platform.pageInfo;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageInfo {
    private Integer page;
    private Integer size;
    private Sort sort;
}
