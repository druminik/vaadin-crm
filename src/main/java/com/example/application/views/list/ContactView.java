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
import com.vaadin.flow.router.RouterLink;

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

  private CrmService service;

  public ContactView(CrmService crmService) {
    this.service = crmService;
    addClassName("list-view");
    setSizeFull();
    configureGrid();
    configureForm();
    add(getToolbar(), getContents());
    updateList();
    closeEditor();
  }

  private void closeEditor() {
    contactForm.setContact(null);
    contactForm.setVisible(false);
  }

  private Component getContents() {
    var content = new HorizontalLayout(grid, contactForm);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, contactForm);
    content.setClassName("content");
    content.setSizeFull();
    return content;
  }

  private void configureForm() {
    contactForm = new ContactForm(service.findAllCompanys(), service.findAllStatus());
    contactForm.setWidth("25em");

    contactForm.addSaveListener(this::saveContact);
    contactForm.addDeleteListener(this::deleteContact);
    contactForm.addCloseListener(event -> closeEditor());
  }

  private void saveContact(ContactForm.SaveEvent event) {
    service.saveContact(event.getContact());
    updateList();
    closeEditor();
  }

  private void deleteContact(ContactForm.DeleteEvent event) {
    service.deleteContact(event.getContact());
    updateList();
    closeEditor();
  }

  private void configureGrid() {
    grid.addClassNames("contact-grid");
    grid.setSizeFull();
    grid.setColumns("firstName", "lastName", "email");
    grid.getColumnByKey("firstName").setSortable(true);
    Column<Contact> lastNameColumn = grid.getColumnByKey("lastName").setSortable(true);
    grid.getColumnByKey("email").setSortable(true);

    grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");

    grid.addColumn(new ComponentRenderer<Span, Contact>(contact -> {
      Company company = contact.getCompany();
      if (company != null) {
        RouterLink link = new RouterLink(company.getName(), CompanyView.class, String.valueOf(
            company.getId()));
        link.getElement().getStyle().set("text-decoration", "none");
        return new Span(link);
      } else {
        return new Span("No company available");
      }
    })).setHeader("Company");
    grid.addColumn(contact -> contact.getAddress().getStreet()).setHeader("Street");
    grid.addColumn(contact -> contact.getAddress().getCity()).setHeader("City");
    grid.getColumns().forEach(col -> col.setAutoWidth(true));

    grid.sort(Arrays.asList(new GridSortOrder<Contact>(lastNameColumn, SortDirection.ASCENDING)));

    grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));
  }

  public void editContact(Contact contact) {
    if (contact == null) {
      closeEditor();
    } else {
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

}
