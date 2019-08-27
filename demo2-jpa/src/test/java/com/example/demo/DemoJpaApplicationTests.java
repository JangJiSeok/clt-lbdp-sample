package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class DemoJpaApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	   @Autowired
	    private TestEntityManager entityManager;

	    @Autowired
	    private BookRepository repository;

	    @Test
	    public void testFindByName() {

	        entityManager.persist(new Book("C++"));

	        List<Book> books = repository.findByName("C++");
	        assertEquals(1, books.size());
//	        assertThat(books).extracting(Book::getName).containsOnly("C++");

	    }
	
	
	

}
