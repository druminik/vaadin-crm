package com.example.application.data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Address extends AbstractEntity {

  private String street;
  private String city;
  private String state;
  private String zipCode;

  @OneToMany(mappedBy = "address")
  private List<Contact> contacts;

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public List<Contact> getContacts() {
    return this.contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

}
