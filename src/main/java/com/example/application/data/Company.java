package com.example.application.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Company extends AbstractEntity {
  @NotBlank
  private String name;

  @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
  @Nullable
  private List<Contact> employees = new LinkedList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Contact> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Contact> employees) {
    this.employees = employees;
  }
}
