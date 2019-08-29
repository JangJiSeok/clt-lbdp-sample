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

}
