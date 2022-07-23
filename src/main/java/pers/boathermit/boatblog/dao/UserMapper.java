package pers.boathermit.boatblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.boathermit.boatblog.model.po.User;

/**
 * @author yinzihang
 * @since 0.1
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
