package com.example.application.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

  @Query("select a from Address a " +
      "where lower(a.street) like lower(concat('%', :searchTerm, '%'))")
  List<Address> search(@Param("searchTerm") String searchTerm);

}
