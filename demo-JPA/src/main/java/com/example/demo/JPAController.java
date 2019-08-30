package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "test")
public class JPAController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    JPAService jpaService;

    /**
     * @param request
     * @param response
     * @param List<Book>
     * @return
     * @throws Exception
     * Service 호출사례
     */
    @RequestMapping(value = "/city/pop/{population}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<City>  findByName3(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(value = "population") int population
    ) throws Exception {
        System.out.println("city RequestMapping start!!!");
        return jpaService.findByPopulation(population);
        //return this.cityRepository.findByPopulation(population);
    }



    /**
     * @param request
     * @param response
     * @param List<Book>
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/book/{bookName}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Book> findByName1(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(value = "bookName") String bookName
    ) throws Exception {
        System.out.println("book RequestMapping start!!!");
        return this.bookRepository.findByName(bookName);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/city/{countryCode}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<City>  findByName2(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(value = "countryCode") String countryCode
    ) throws Exception {
        System.out.println("city RequestMapping start!!!");
        return this.cityRepository.findByCountrycode(countryCode);
    }



    /**
     * @param request
     * @param response
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/username/{username}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<User>  findByName4(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(value = "username") String username
    ) throws Exception {
        System.out.println("username RequestMapping start!!!");
        return this.userRepository.findByUsername(username);
    }


    /**
     * @param request
     * @param response
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/save/{chidCnt}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RetrunVO  findByName7(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(value = "chidCnt") long chidCnt
    ) throws Exception {
        System.out.println("/order/save RequestMapping start!!!");

        // TODO: 2019-08-30 add the header of Returned VO(Value Object), so have to declare "extends AbstractHeader Class" in all VO

        Order order=new Order();
        //orderMaster.setId(orderId);
        order.setEmp("jang jaeock");

        List<OrderItem> orderItemList = new ArrayList();
        for (int idx=1; idx<=chidCnt;idx++ ) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductname("New Data Server");
            orderItem.setQty(3000L);
            orderItem.setPrice(500L);
            orderItemList.add(orderItem);
        }

        order.setOrderItemList(orderItemList);

        jpaService.saveOrder(order);

        RetrunVO vo= new RetrunVO();
        vo.setContents("OK");
        return vo;
    }

    /**
     * @param
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Optional<Order> findByOrderId(HttpServletRequest request, HttpServletResponse response,
                                         @PathVariable(value = "orderId") long orderId
    ) throws Exception {
        System.out.println("username RequestMapping start!!!");
        return this.orderRepository.findById(orderId);
    }





}
