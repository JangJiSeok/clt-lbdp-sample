package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
     * @param List<Book>
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
    @RequestMapping(value = "/order/save", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RetrunVO findByName5(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        System.out.println("/order/save RequestMapping start!!!");
        OrderMaster orderMaster=new OrderMaster();
//        orderMaster.setId(2L);
        orderMaster.setEmp("kim");
        jpaService.saveOrderMaster(orderMaster);
        RetrunVO vo= new RetrunVO();
        vo.setContents("OK");
        return vo;
    }




}
