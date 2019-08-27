package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(DemoJpaApplication.class);

    @Autowired
    private BookRepository repository;

    @Autowired
    private CityRepository repository2;
    
	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}
	
    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        repository.save(new Book("Java"));
        repository.save(new Book("Node"));
        repository.save(new Book("Python"));

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


        
    }

}
