package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    OrderItemRepository orderItemRepository;


    @Autowired
    JPAService jpaService;

    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");



    /**
     * @param request
     * @param response
     * @param List<Book>
     * @return
     * @throws Exception
     * Service 호출사례
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Map<String,Object>>  test(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        System.out.println("Service RequestMapping start!!!");
        return jpaService.testJdbcTemplate();
        //return this.cityRepository.findByPopulation(population);
    }

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
    @PostMapping(value = "/order",  produces = "application/json;charset=UTF-8")
    public Order  createOrder(@Valid @RequestBody Order order)  throws Exception {
        System.out.println("/order/save RequestMapping start!!!");
        return orderRepository.save(order);
    }


    /**
     * @param request
     * @param response
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/order/{chidCnt}",  produces = "application/json;charset=UTF-8")
    public Order  createTempData(@PathVariable(value = "chidCnt") int chidCnt
    ) throws Exception {
        System.out.println("/order/save RequestMapping start!!!");

        // TODO: 2019-08-30 add the header of Returned VO(Value Object), so have to declare "extends AbstractHeader Class" in all VO


        Order order=new Order();
        order.setEmp("jang jaeock");
        order.setCreatedate(dateTimeFormatter.format(LocalDateTime.now()));
        orderRepository.save(order);
        long orderId=order.getId();
        System.out.println("orderId:"+ orderId);

        List<OrderItem> orderItemList = new ArrayList();
        for (int idx=1; idx<=chidCnt;idx++ ) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(order.getId());
            orderItem.setProductname("New Data Server");
            orderItem.setQty(3000L);
            orderItem.setPrice(500L);
            //orderItemRepository.save(orderItem);
            orderItemList.add(orderItem);
        }

        order.setOrderItemList(orderItemList);
        return orderRepository.save(order);

    }

    /**
     * @param
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/order/{orderId}")
    public Page<Order> deleteOrder(@PathVariable(value = "orderId") long orderId
                                     ,@PageableDefault(size=20, sort="id",direction = Sort.Direction.ASC) Pageable pageable
    ) throws Exception {
        System.out.println("username RequestMapping start!!!");
        return this.orderRepository.findById(orderId, pageable);
    }


    /**
     * @param
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/order/{orderId}")
    public ResponseEntity<?> orderDML10002(@PathVariable(value = "orderId") long orderId
    )  {

        System.out.println("order delete start!!!");

        return this.orderRepository.findById(orderId).map(order -> {
            this.orderRepository.deleteById(orderId);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("OrderId: " + orderId + " not found") );
    }


    /**
     * @param
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/order/{orderId}",  produces = "application/json;charset=UTF-8")
    public Order updateOrder(@PathVariable(value = "orderId") long orderId
                            ,@RequestParam(value = "emp", required = false  ,defaultValue = "Ha kyung") String emp ) throws Exception  {

        System.out.println("order/modify RequestMapping start!!!");

        if(!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("OrderId: " + orderId + " not found");
        }

        return this.orderRepository.findById(orderId).map(order -> {
            order.setEmp(emp);
            order.setCurrency(Currency.EURO);

            List<OrderItem> orderItemList= order.getOrderItemList();
            for(OrderItem orderItem : orderItemList ) {
                orderItem.setQty(999999L);
                orderItem.setPrice(61300L);
            }
            return this.orderRepository.save(order);

            }).orElseThrow(()->new ResourceNotFoundException("OrderId: " + orderId + " not found") );

    }



    // TODO: 2019-08-31 add Multi orderMaster operation for the test of sequence


}

