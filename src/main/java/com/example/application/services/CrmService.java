package com.example.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.Address;
import com.example.application.data.AddressRepository;
import com.example.application.data.Company;
import com.example.application.data.CompanyRepository;
import com.example.application.data.Contact;
import com.example.application.data.ContactRepository;
import com.example.application.data.Status;
import com.example.application.data.StatusRepository;

@Service
public class CrmService {
  private final CompanyRepository companyRepository;
  private final ContactRepository contactRepository;
  private final StatusRepository statusRepository;
  private final AddressRepository addressRepository;

  public CrmService(CompanyRepository companyRepository, ContactRepository contactRepository,
      StatusRepository statusRepository, AddressRepository addressRepository) {
    this.companyRepository = companyRepository;
    this.contactRepository = contactRepository;
    this.statusRepository = statusRepository;
    this.addressRepository = addressRepository;
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
    return companyRepository.findAll();
  }

  public List<Status> findAllStatus() {
    return statusRepository.findAll();
  }

  public List<Address> findAllAddresses() {
    return addressRepository.findAll();
  }

}
