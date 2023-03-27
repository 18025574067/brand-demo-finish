package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    // 1. 调用BrandMapper.selectAll()
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    public List<Brand> selectAll(){

        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        List<Brand> brands = mapper.selectAll();

        // 5. 释放资源
        sqlSession.close();

        return brands;
    }

    /**
     * 添加
     * @param brand
     */
    public void add(Brand brand){
        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.add(brand);

        // 5. 提交数据
        sqlSession.commit();

        // 6. 释放资源
        sqlSession.close();
    }

    /**
     * 根据id查询
     * @return
     */
    public Brand selectById(int id){

        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        Brand brand = mapper.selectById(id);

        // 5. 释放资源
        sqlSession.close();

        return brand;
    }

    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand){
        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.update(brand);

        // 5. 提交数据
        sqlSession.commit();

        // 6. 释放资源
        sqlSession.close();
    }

    /**
     * 修改
     * @param id
     */
    public void deleteById(int id){
        // 2. 获取sqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用方法
        mapper.deleteById(id);

        // 5. 提交数据
        sqlSession.commit();

        // 6. 释放资源
        sqlSession.close();
    }
}
