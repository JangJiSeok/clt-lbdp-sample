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
    public void saveOrderMaster(Order orderMaster) {
        //OrderMaster orderMaster=new OrderMaster();
        long oderId=3L;
        long productId=501L;

        orderMaster.setId(oderId);
        orderMaster.setEmp("jang jae ock");
        orderMasterRepository.save(orderMaster);


        OrderItem orderItem= new OrderItem();
        orderItem.setId(oderId);
        orderItem.setProductcode(productId);

        orderItem.setProductname("OLE TV 3");
        orderItem.setPrice(3000L);
        orderItem.setQty(10L);

        orderItemRepository.save(orderItem);

        OrderItemPK orderItemPK= new OrderItemPK();
        orderItemPK.setId(oderId);
        orderItemPK.setProductcode(productId);

        Optional<OrderItem> oi= orderItemRepository.findById(orderItemPK );
        System.out.println("========+++++++++++++++++++++++++++++++");
        System.out.println(oi);


        //orderItemRepository.save()


    }



}
