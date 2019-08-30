package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JPAService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Transactional(readOnly = true)
    public List<City> findByPopulation(int population){
        System.out.println("Service username RequestMapping start!!!");
        return this.cityRepository.findByPopulation(population);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrderMaster(OrderMaster orderMaster) {
        //OrderMaster orderMaster=new OrderMaster();
//        orderMaster.setId(2);
//        orderMaster.setEmp("jang");
        orderMasterRepository.save(orderMaster);

    }



}
