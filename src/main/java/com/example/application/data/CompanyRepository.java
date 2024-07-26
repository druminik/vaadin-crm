package com.example.application.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {

  @Query("select c from Company c " +
      "where lower(c.name) like lower(concat('%', :searchTerm, '%'))")
  List<Company> search(@Param("searchTerm") String searchTerm);
}
