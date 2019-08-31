package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner{

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CityRepository repository2;

    @Autowired
    private UserRepository repository3;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderMasterRepository;

    @Autowired
    private JdbcTemplate jdbc;


	public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);

//        try (ConfigurableApplicationContext ctx = SpringApplication.run(DemoJpaApplication.class, args))       {
//            DemoJpaApplication m = ctx.getBean(DemoJpaApplication.class);
//            m.method();
//            m.run(args);
//        }


	}
	
    @Override
    public void run(String... args) {

        log.debug("StartApplication...");

//        log.debug("\nfindAll()");
      // repository.findAll().forEach(x -> System.out.println(x));

        log.debug("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));
//
//        log.debug("\nfindByName('Node')");
//        repository.findByName("Node").forEach(x -> System.out.println(x));
//
//
//        log.debug("\n==================city=================");
//        log.debug("\nfindById(10L)");
//        repository2.findById(10L).ifPresent(x -> System.out.println(x));
//
//        log.debug("\nCity findByName('Rafah')");
//        repository2.findByName("Rafah").forEach(x -> System.out.println(x));
//
//        log.debug("\nCity findByCountrycodeReturnStream('AFG')");
//       repository2.findByCountrycode("AFG").forEach(x -> System.out.println(x));
//
//
//        log.debug("\nCity findByUsername('jojang.com')");
//        repository3.findByUsername("jojang.com").forEach(x -> System.out.println(x));

//        EntityManagerFactory factory = applicationContext.getBean(EntityManagerFactory.class); EntityManager manager = factory.createEntityManager();
//        EntityManager em = factory.createEntityManager();

//        log.debug("====================================================================================");

//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(1L);
//        orderItem.setProductCode(301L);
//        orderItem.setProductName("ABC");
//        orderItem.setQty(200);
//        orderItem.setPrice(5000);


//        log.debug("====================================================================================");

//        OrderItemPK pk= new OrderItemPK();
//        pk.setId(1L);
//        pk.setProductCode(301L);
//
//        Optional<OrderItem> orderItem2 = Optional.of(new OrderItem());
//        orderItem2=orderItemRepository.findById(pk);
//        log.debug("\nPK Search ('Order')");
//        log.debug("ProductName:" +orderItem2);



//        em.persist(orderItem);
//        em.flush();
//        em.clear();
//
//        OrderItemPK pk= new OrderItemPK();
//        pk.setId(1L);
//        pk.setProductCode(301L);
//
//        orderItem =em.find(OrderItem.class,pk);
//        log.debug("\nPK Search ('Order')");
//        log.debug("ProductName:" +orderItem.getProductName());

            method();

    }





	public void method() {


//	    this.jdbc.execute("CREATE TABLE TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256))");
//	    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "hoge");
//	    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "fuga");
//	    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "piyo");
//	    List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM TEST_TABLE");
//	    list.forEach(System.out::println);

        log.debug("\n====================================================================================");
        log.debug("\n====================================================================================");
        
        List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM world.ordermaster");
        list.forEach(System.out::println);
        log.debug("\n====================================================================================");

	}



}
