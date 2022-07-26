package com.boathermit.boatblog.model.param;

import lombok.Data;

/**
 * 分页信息，包括当前页数和每页显示的数量
 *
 * @author yinzihang
 * @since 0.1
 */
@Data
public class PageParam {

    private int page = 1;

    private int pageSize = 10;
}
