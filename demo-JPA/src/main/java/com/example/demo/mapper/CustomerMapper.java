package com.example.demo.mapper;

import com.example.demo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {

    @Select("select * from customer_tbl where userName = #{userName}")
    Customer findByUserNameMybatis(@Param("userName") String userName);

    List<Customer> findAllByMybatis();


}