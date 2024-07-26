package com.example.application.views.list;

import com.example.application.data.Address;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;

public class AddressForm extends FormLayout {
  TextField street = new TextField("Street");
  TextField city = new TextField("City");
  TextField zipCode = new TextField("Zip Code");
  TextField state = new TextField("State");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  BeanValidationBinder<Address> binder = new BeanValidationBinder<>(Address.class);

  public AddressForm() {
    addClassName("contact-form");
    binder.bindInstanceFields(this);

    add(street, zipCode, city, state, createButtonsLayout());
  }

  public void setAddress(Address address) {
    binder.setBean(address);
  }

  private Component createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
    return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() {
    if (binder.isValid()) {
      fireEvent(new SaveEvent(this, binder.getBean()));
    }
  }

  public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
    return addListener(DeleteEvent.class, listener);
  }

  public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
    return addListener(SaveEvent.class, listener);
  }

  public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
    return addListener(CloseEvent.class, listener);
  }

  /**
   * Inner class for Events
   */
  public static abstract class AddressFormEvent extends ComponentEvent<AddressForm> {

    private Address address;

    protected AddressFormEvent(AddressForm source, Address address) {
      super(source, false);
      this.address = address;

    }

    public Address getAddress() {
      return address;
    }
  }

  public static class SaveEvent extends AddressFormEvent {

    SaveEvent(AddressForm source, Address address) {
      super(source, address);
    }
  }

  public static class DeleteEvent extends AddressFormEvent {

    DeleteEvent(AddressForm source, Address address) {
      super(source, address);
    }
  }

  public static class CloseEvent extends AddressFormEvent {

    CloseEvent(AddressForm source) {
      super(source, null);
    }

  }

}
