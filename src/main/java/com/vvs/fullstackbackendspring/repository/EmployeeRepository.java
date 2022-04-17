package com.vvs.fullstackbackendspring.repository;

import java.util.List;

import com.vvs.fullstackbackendspring.model.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
  boolean existsByEmail(String email);
  void deleteById(String id);
  Employee findByEmail(String email);
  List<Employee> findByFirstNameStartingWith(String firstName);
  @Query(value = "{'experience':{$gte:?0}}")
  List<Employee> findByExperience(int experience);
  List<Employee> getByEmail(String email);

}
