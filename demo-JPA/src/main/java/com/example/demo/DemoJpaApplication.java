package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(DemoJpaApplication.class);

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

    //public static ApplicationContext applicationContext;
    @Autowired
    JPAService jpaService;

	public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
	}
	
    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
        repository.findByName("Node").forEach(x -> System.out.println(x));

        
        System.out.println("\n==================city=================");
        System.out.println("\nfindById(10L)");
        repository2.findById(10L).ifPresent(x -> System.out.println(x));
        
        System.out.println("\nCity findByName('Rafah')");
        repository2.findByName("Rafah").forEach(x -> System.out.println(x));
        
        System.out.println("\nCity findByCountrycodeReturnStream('AFG')");
       repository2.findByCountrycode("AFG").forEach(x -> System.out.println(x));


        System.out.println("\nCity findByUsername('jojang.com')");
        repository3.findByUsername("jojang.com").forEach(x -> System.out.println(x));

//        EntityManagerFactory factory = applicationContext.getBean(EntityManagerFactory.class); EntityManager manager = factory.createEntityManager();
//        EntityManager em = factory.createEntityManager();

        System.out.println("====================================================================================");

//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(1L);
//        orderItem.setProductCode(301L);
//        orderItem.setProductName("ABC");
//        orderItem.setQty(200);
//        orderItem.setPrice(5000);


        System.out.println("====================================================================================");

//        OrderItemPK pk= new OrderItemPK();
//        pk.setId(1L);
//        pk.setProductCode(301L);
//
//        Optional<OrderItem> orderItem2 = Optional.of(new OrderItem());
//        orderItem2=orderItemRepository.findById(pk);
//        System.out.println("\nPK Search ('Order')");
//        System.out.println("ProductName:" +orderItem2);



//        em.persist(orderItem);
//        em.flush();
//        em.clear();
//
//        OrderItemPK pk= new OrderItemPK();
//        pk.setId(1L);
//        pk.setProductCode(301L);
//
//        orderItem =em.find(OrderItem.class,pk);
//        System.out.println("\nPK Search ('Order')");
//        System.out.println("ProductName:" +orderItem.getProductName());








    }

}
