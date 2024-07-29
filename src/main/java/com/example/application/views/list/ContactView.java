package com.example.application.views.list;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;

import com.example.application.data.Company;
import com.example.application.data.Contact;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.security.PermitAll;

@org.springframework.stereotype.Component
@Scope("prototype")
@PermitAll
@PageTitle("Contacts | Customer CRM")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "")
public class ContactView extends VerticalLayout {

  Grid<Contact> grid = new Grid<>(Contact.class);
  TextField filterText = new TextField();
  ContactForm contactForm;
  CompanyForm companyForm;

  private CrmService service;
  private Company company;

  public ContactView(CrmService crmService) {
    this.service = crmService;
    addClassName("list-view");
    setSizeFull();
    configureGrid();
    configureContactForm();
    configureCompanyForm();
    add(getToolbar(), getContents());
    updateList();
    closeContactEditor();
  }

  private void closeContactEditor() {
    contactForm.setContact(null);
    contactForm.setVisible(false);
  }

  private Component getContents() {
    var content = new HorizontalLayout(grid, contactForm, companyForm);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, contactForm);
    content.setFlexGrow(1, companyForm);
    content.setClassName("content");
    content.setSizeFull();
    return content;
  }

  private void configureContactForm() {
    contactForm = new ContactForm(service.findAllCompanys(), service.findAllStatus());
    contactForm.setWidth("25em");

    contactForm.addSaveListener(this::saveContact);
    contactForm.addDeleteListener(this::deleteContact);
    contactForm.addCloseListener(event -> closeContactEditor());
  }

  private void saveContact(ContactForm.SaveEvent event) {
    service.saveContact(event.getContact());
    updateList();
    closeContactEditor();
  }

  private void deleteContact(ContactForm.DeleteEvent event) {
    service.deleteContact(event.getContact());
    updateList();
    closeContactEditor();
  }

  private void configureGrid() {
    grid.addClassNames("contact-grid");
    grid.setSizeFull();
    grid.setColumns("firstName", "lastName", "email");
    grid.getColumnByKey("firstName").setSortable(true);
    Column<Contact> lastNameColumn = grid.getColumnByKey("lastName").setSortable(true);
    grid.getColumnByKey("email").setSortable(true);

    grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
    grid.addColumn(new ComponentRenderer<>(contact -> {
      Company myCompany = contact.getCompany();
      if (myCompany != null) {
        Button linkButton = new Button(myCompany.getName());
        linkButton.getElement().setAttribute("theme", "tertiary"); // Style the button as a link
        linkButton.addClickListener(event -> editCompany(myCompany));
        return linkButton;
      } else {
        return new Span("");
      }
    })).setHeader("Company");
    grid.addColumn(contact -> contact.getAddress().getStreet()).setHeader("Street");
    grid.addColumn(contact -> contact.getAddress().getCity()).setHeader("City");
    grid.getColumns().forEach(col -> col.setAutoWidth(true));

    grid.sort(Arrays.asList(new GridSortOrder<Contact>(lastNameColumn, SortDirection.ASCENDING)));

    grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));
  }

  private void editContact(Contact contact) {
    if (contact == null) {
      closeContactEditor();
    } else {
      closeCompanyEditor();
      contactForm.setContact(contact);
      contactForm.setVisible(true);
      addClassName("editing");
    }
  }

  private HorizontalLayout getToolbar() {
    filterText.setPlaceholder("Filter by name...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(e -> updateList());

    var addContactButton = new Button("Add contact");
    addContactButton.addClickListener(event -> addContact());

    var toolbar = new HorizontalLayout(filterText, addContactButton);
    toolbar.addClassName("toolbar");
    return toolbar;
  }

  private void addContact() {
    grid.asSingleSelect().clear();
    editContact(new Contact());
  }

  private void updateList() {
    grid.setItems(service.findAllContacts(filterText.getValue()));
  }

  private void configureCompanyForm() {
    companyForm = new CompanyForm();
    companyForm.setWidth("25em");

    companyForm.addSaveListener(event -> {
      service.saveCompany(event.getCompany());
      updateList();
      closeCompanyEditor();
    });
    companyForm.addCloseListener(event -> {
      closeCompanyEditor();
    });
    closeCompanyEditor();
  }

  private void closeCompanyEditor() {
    companyForm.setCompany(null);
    companyForm.setVisible(false);
  }

  private void editCompany(Company company) {
    if (company == null) {
      closeCompanyEditor();
    } else {
      closeContactEditor();
      companyForm.setCompany(company);
      companyForm.setVisible(true);
      addClassName("editing");
    }
  }

}
