package com.example.application.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyObjectRepository extends JpaRepository<PropertyObject, Long> {

  @Query("select p from PROPERTYOBJECT p " +
      "where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
  List<PropertyObject> search(@Param("searchTerm") String searchTerm);

}
