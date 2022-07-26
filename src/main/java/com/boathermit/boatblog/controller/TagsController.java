package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.model.vo.TagVo;
import com.boathermit.boatblog.service.TagService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yin Zihang
 * @since 2022/7/26 12:16
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagService tagService;

    @Autowired
    TagsController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/hot")
    public Result listHotTags() {
        int limit = 6;
        List<TagVo> tagVoList = tagService.hot(limit);
        return Result.success(tagVoList);
    }
}
