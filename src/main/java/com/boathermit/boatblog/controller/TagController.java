package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.enums.Limits;
import com.boathermit.boatblog.service.TagService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yin Zihang
 * @since 2022/7/26 12:16
 * @see com.boathermit.boatblog.service.impl.TagServiceImpl
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 首页 最热标签列表
     *
     * @return 最热标签列表
     */
    @GetMapping("/hot")
    public Result listHotTags() {
        return Result.success(tagService.hot(Limits.HOT_TAG_LIMIT.getLimit()));
    }
}
