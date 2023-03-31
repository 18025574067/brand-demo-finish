package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password){

        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 4. 调用方法
        User user = mapper.select(username, password);

        // 5. 释放资源
        sqlSession.close();

        return user;
    }


    /**
     * 注册方法
     * @return
     */
    public boolean register(User user){

        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 4. 判断用户是否存在
        User u = mapper.selectByUsername(user.getUsername());
        if (u == null){
            // 用户名未被注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u == null;
    }

    /**
     * 查询用户名是否存在
     * @param user
     * @return
     */
    public boolean selectUser(User user){

        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 4. 判断用户是否存在
        User u = mapper.selectByUsername(user.getUsername());
        if (u == null){
            // 用户名未被注册
            return false;
        }
        // 用户名未注册
        sqlSession.close();
        return true;
    }
}
