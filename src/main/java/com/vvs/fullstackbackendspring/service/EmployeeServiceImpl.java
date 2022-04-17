package com.vvs.fullstackbackendspring.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vvs.fullstackbackendspring.model.Employee;
import com.vvs.fullstackbackendspring.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  
  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public String postEmployee(Employee employee) {
      return employeeRepository.existsByEmail(employee.getEmail()) ? 
      "Employee with email: ".concat(employee.getEmail()).concat(" already exist.") :
      employeeRepository.save(employee).fullName().concat(" successfully registered."); 
  }

  @Override
  public String updateEmployee(Employee employee) {
    Employee updatedEmployee = employeeRepository.existsById(employee.getId()) ?
      Employee.builder()
        .id(employee.getId())
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .password(employee.getPassword())
        .dob(employee.getDob())
        .phone(employee.getPhone())
        .email(employee.getEmail())
        .experience(employee.getExperience())
        .domain(employee.getDomain())
        .build() :
      Employee.builder()
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .password(employee.getPassword())
        .dob(employee.getDob())
        .phone(employee.getPhone())
        .email(employee.getEmail())
        .experience(employee.getExperience())
        .domain(employee.getDomain())
        .build();
    employeeRepository.save(updatedEmployee);

    return updatedEmployee.fullName().concat(" successfully updated or registered.");
  }

  @Override
  public List<Employee> deleteEmployeeById(String id) {
    employeeRepository.deleteById(id);

    return employeeRepository.findAll();
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
  }

  @Override
  public List<Employee> getByEmail(String email) {
    return employeeRepository.existsByEmail(email) ? employeeRepository.getByEmail(email) : null;
  }

  @Override
  public Employee validateUser(String username, String password) {
    Employee employee = employeeRepository.findByEmail(username);
    return employee != null && employee.getPassword().equals(password) ? employee : null;
  }

  @Override
  public List<Employee> getByFirstname(String firstname) {
    return employeeRepository.findByFirstNameStartingWith(firstname);
  }

  @Override
  public List<Employee> getByExperience(int experience) {
    return employeeRepository.findByExperience(experience);
  }

  @Override
  public Map<String, Object> getAllEmployeesInPage(int pageNo, int pageSize, String sortBy) {
    Map<String, Object> response = new LinkedHashMap<String, Object>();
    Sort sort = Sort.by(sortBy);
    PageRequest page = PageRequest.of(pageNo, pageSize, sort);
    Page<Employee> employeesPage = employeeRepository.findAll(page);

    response.put("data", employeesPage.getContent());
    response.put("Total number of pages", employeesPage.getTotalPages());
    response.put("current page number", employeesPage.getNumber());
    response.put("Total number of employees", employeesPage.getTotalElements());

    return response;
  }

}
