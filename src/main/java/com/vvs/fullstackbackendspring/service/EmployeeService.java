package com.vvs.fullstackbackendspring.service;

import java.util.List;
import java.util.Map;

import com.vvs.fullstackbackendspring.model.Employee;

public interface EmployeeService {
  
  public String postEmployee(Employee employee);
  public String updateEmployee(Employee employee);
  public List<Employee> deleteEmployeeById(String id);
  public Employee validateUser(String username, String password);
  public List<Employee> getAllEmployees();
  public Map<String, Object> getAllEmployeesInPage(int pageNo, int pageSize, String sortBy);
  public List<Employee> getByEmail(String email);
  public List<Employee> getByFirstname(String firstname);
  public List<Employee> getByExperience(int experience);
}
