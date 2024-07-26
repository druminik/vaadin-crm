package com.example.application.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyRepository extends JpaRepository<Property, Long> {

  @Query("select p from Property p " +
      "where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
  List<Property> search(@Param("searchTerm") String searchTerm);

}
