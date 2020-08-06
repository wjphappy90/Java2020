package cn.itcast.service;

import java.util.List;


import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserServiceImpl implements UserService {
    // 创建queryRunner
    //1. 获取JDBCTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findListByName(String name) {
        //1 定义sql语句
        String sql = "select * from t_user where name like ? ";
        //2 查询
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), "%" + name + "%");
        //3 返回
        return list;

    }

}
