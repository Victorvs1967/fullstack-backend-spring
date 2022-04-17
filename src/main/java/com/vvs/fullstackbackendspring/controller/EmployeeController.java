package com.vvs.fullstackbackendspring.controller;

import java.util.List;
import java.util.Map;

import com.vvs.fullstackbackendspring.model.Employee;
import com.vvs.fullstackbackendspring.model.User;
import com.vvs.fullstackbackendspring.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class EmployeeController {
  
  @Autowired
  private EmployeeService employeeService;

  @PostMapping("/register")
  public String register(@RequestBody Employee employee) {
    return employeeService.postEmployee(employee);
  }

  @PostMapping("/login")
  public Employee employeeLogin(@RequestBody User user) { 
    return employeeService.validateUser(user.getUsername(), user.getPassword());
  }

  @PutMapping("/update")
  public String updateEmployee(@RequestBody Employee employee) {
    return employeeService.updateEmployee(employee);
  }

  @DeleteMapping("/delete/{id}")
  public List<Employee> deleteEmployee(@PathVariable String id) {
    return employeeService.deleteEmployeeById(id);
  }

  @GetMapping("/employees")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/employeesInPage")
  public Map<String, Object> getAllEmployeesInPage(
    @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
    @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
    @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
      return employeeService.getAllEmployeesInPage(pageNo, pageSize, sortBy);
  }

  @GetMapping("/employeesByEmail/{email}")
  public List<Employee> getEmployeesByEmail(@PathVariable String email) {
    return employeeService.getByEmail(email);
  }

  @GetMapping("/employeesByFirstname")
  public List<Employee> getEmployeesByFirstname(@RequestParam(name = "firstName") String firstName) {
    return employeeService.getByFirstname(firstName);
  }

  @GetMapping("/employeesByExperience")
  public List<Employee> GetEmployeesByExperience(@RequestParam(name = "experience") int experience) {
    return employeeService.getByExperience(experience);
  }
}
