package com.vvs.fullstackbackendspring.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document("employees")
public class Employee {
  
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String password;
  private LocalDate dob;
  private String phone;
  private String email;
  private int experience;
  private String domain;
  
  public String fullName() {
    return this.firstName != null ? this.firstName.concat(" ").concat(lastName) : "";
  }
}
