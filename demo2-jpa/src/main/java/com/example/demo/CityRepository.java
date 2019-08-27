package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends CrudRepository<City, Long> {
	
	 List<City> findByName(String name);
	 
	 @Query(value ="SELECT c.* FROM world.city as c where c.countrycode = :countrycode", nativeQuery = true)
	 List<City> findByCountrycode(@Param("countrycode") String countrycode);
	 
	 
	 
	 //Stream<City> findByCountrycodeReturnStream(@Param("countrycode") String countrycode);
	 
}
