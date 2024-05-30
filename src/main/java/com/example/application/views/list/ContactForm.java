package com.example.application.views.list;

import java.text.Bidi;
import java.util.List;

import com.example.application.data.Company;
import com.example.application.data.Contact;
import com.example.application.data.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Bottom;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class ContactForm extends FormLayout {
  TextField firstName = new TextField("First Name");
  TextField lastName = new TextField("Last Name");
  EmailField email = new EmailField("Email");
  ComboBox<Status> status = new ComboBox<>("Status");
  ComboBox<Company> company = new ComboBox<>("Company");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  BeanValidationBinder<Contact> binder = new BeanValidationBinder<>(Contact.class);

  public ContactForm(List<Company> companies, List<Status> statuses) {
    addClassName("contact-form");
    status.setItems(statuses);
    status.setItemLabelGenerator(Status::getName);
    company.setItems(companies);
    company.setItemLabelGenerator(Company::getName);

    binder.bindInstanceFields(this);

    add(firstName, lastName, email, status, company, createButtonsLayout());
  }

  public void setContact(Contact contact){
    binder.setBean(contact);
  }

  private Component createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    return new HorizontalLayout(save, delete, close);
  }


}
