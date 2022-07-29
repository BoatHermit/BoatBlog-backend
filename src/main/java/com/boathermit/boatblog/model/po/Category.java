package com.boathermit.boatblog.model.po;

import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:02
 */
@Data
public class Category {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
