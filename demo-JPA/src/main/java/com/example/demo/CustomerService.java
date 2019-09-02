package com.example.demo;

import com.example.demo.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;




    @Cacheable(value = "app", key = "#userName")
    public Customer getName(String userName){
        log.info("find name from jdbc {}", userName);
        return customerRepository.findByUserName(userName);
    }

    @CachePut(value = "app", key = "#customer.userName")
    @Transactional
    public Customer saveCache(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * use mybatis
     * @param userName
     * @return
     */
    public Customer getNameByMabatis(String userName){
        log.info("find name from mybatis jdbc {}", userName);
        return customerMapper.findByUserNameMybatis(userName);
    }

    /**
     * use mybatis
     * @return
     */
    public List<Customer> getListByMabatis(){
        log.info("find list from mybatis jdbc");
        return customerMapper.findAllByMybatis();
    }

}

