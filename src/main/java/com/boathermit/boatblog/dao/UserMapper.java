package com.boathermit.boatblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boathermit.boatblog.model.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author yinzihang
 * @since 0.1
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
