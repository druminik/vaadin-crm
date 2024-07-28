package com.example.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.application.data.Address;
import com.example.application.data.AddressRepository;
import com.example.application.data.Company;
import com.example.application.data.CompanyRepository;
import com.example.application.data.Contact;
import com.example.application.data.ContactRepository;
import com.example.application.data.Property;
import com.example.application.data.PropertyRepository;
import com.example.application.data.Status;
import com.example.application.data.StatusRepository;

@Service
public class CrmService {
  private final CompanyRepository companyRepository;
  private final ContactRepository contactRepository;
  private final StatusRepository statusRepository;
  private final AddressRepository addressRepository;
  private final PropertyRepository propertyRepository;

  public CrmService(CompanyRepository companyRepository, ContactRepository contactRepository,
      StatusRepository statusRepository, AddressRepository addressRepository, PropertyRepository propertyRepository) {
    this.companyRepository = companyRepository;
    this.contactRepository = contactRepository;
    this.statusRepository = statusRepository;
    this.addressRepository = addressRepository;
    this.propertyRepository = propertyRepository;
  }

  public List<Contact> findAllContacts(String stringFilter) {
    if (stringFilter == null || stringFilter.isEmpty()) {
      return contactRepository.findAll();
    } else {
      return contactRepository.search(stringFilter);
    }
  }

  public long countContacts() {
    return contactRepository.count();
  }

  public void deleteContact(Contact contact) {
    contactRepository.delete(contact);
  }

  public void saveContact(Contact contact) {
    if (contact == null) {
      System.err.println("Contact is null");
      return;
    }
    contactRepository.save(contact);
  }

  public List<Company> findAllCompanys() {
    return findAllCompanys(null);
  }

  public List<Company> findAllCompanys(String stringFilter) {
    if (stringFilter == null || stringFilter.isEmpty()) {
      return companyRepository.findAll();
    } else {
      return companyRepository.search(stringFilter);
    }
  }

  public void saveCompany(Company company) {
    if (company == null) {
      System.err.println("Company is null");
      return;
    }
    companyRepository.save(company);
  }

  public void deleteCompany(Company company) {
    companyRepository.delete(company);
  }

  public Company getCompany(Long id) {
    Optional<Company> company = companyRepository.findById(id);
    if (company.isPresent())
      return company.get();
    return null;
  }

  public List<Status> findAllStatus() {
    return statusRepository.findAll();
  }

  public List<Address> findAllAddresses() {
    return addressRepository.findAll();
  }

  public void saveProperty(Property property) {
    propertyRepository.save(property);
  }

  public void deleteProperty(Property property) {
    propertyRepository.delete(property);
  }

  public List<Property> findAllPropertys(String stringFilter) {
    if (stringFilter == null || stringFilter.isEmpty()) {
      return propertyRepository.findAll();
    } else {
      return propertyRepository.search(stringFilter);
    }

  }

  public void saveAddress(Address address) {
    addressRepository.save(address);
  }

  public void deleteAddress(Address address) {
    addressRepository.delete(address);
  }

  public List<Address> findAllAddresss(String stringFilter) {
    return addressRepository.search(stringFilter);
  }

}
