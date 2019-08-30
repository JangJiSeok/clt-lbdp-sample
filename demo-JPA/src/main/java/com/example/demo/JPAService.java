package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JPAService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderMasterRepository;


    @Transactional(readOnly = true)
    public List<City> findByPopulation(int population){
        System.out.println("Service username RequestMapping start!!!");
        return this.cityRepository.findByPopulation(population);
    }

    @Transactional//(propagation = Propagation.REQUIRES_NEW)
    public void saveOrder(Order orderMaster) {

        orderMasterRepository.save(orderMaster);
        System.out.println("========master");

        for(OrderItem orderItem : orderMaster.getOrderItemList() ) {
            orderItem.setId(orderMaster.getId());
            orderItemRepository.save(orderItem);
        }

        System.out.println("========++++++save end+++++++++++++++++++++++++");
    }



}
