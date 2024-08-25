package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "PROPERTYOBJECT")
public class PropertyObject extends AbstractEntity {

  @NotBlank
  private String name;

  @ManyToOne
  @JoinColumn(name = "property_id", nullable = true)
  private Property property;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Property getProperty() {
    return this.property;
  }

  public void setProperty(Property property) {
    this.property = property;
  }

}