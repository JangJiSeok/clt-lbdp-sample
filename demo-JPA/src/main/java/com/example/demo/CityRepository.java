package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
	
	List<City> findByName(String name);

	@Query(value ="SELECT c.* FROM world.city as c where c.countrycode = :countrycode", nativeQuery = true)
	List<City> findByCountrycode(@Param("countrycode") String countrycode);

	@Query(value ="SELECT c.* FROM world.city as c where c.population >= :population", nativeQuery = true)
	List<City> findByPopulation(@Param("population") int population);



	 
	 //Stream<City> findByCountrycodeReturnStream(@Param("countrycode") String countrycode);
	 
}
