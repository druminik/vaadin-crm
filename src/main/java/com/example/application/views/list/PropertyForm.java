package com.example.application.views.list;

import com.example.application.data.Property;
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

public class PropertyForm extends FormLayout {
  TextField name = new TextField("Property Name");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  BeanValidationBinder<Property> binder = new BeanValidationBinder<>(Property.class);

  public PropertyForm() {
    addClassName("contact-form");
    binder.bindInstanceFields(this);

    add(name, createButtonsLayout());
  }

  public void setProperty(Property property) {
    binder.setBean(property);
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
  public static abstract class PropertyFormEvent extends ComponentEvent<PropertyForm> {

    private Property property;

    protected PropertyFormEvent(PropertyForm source, Property property) {
      super(source, false);
      this.property = property;

    }

    public Property getProperty() {
      return property;
    }
  }

  public static class SaveEvent extends PropertyFormEvent {

    SaveEvent(PropertyForm source, Property property) {
      super(source, property);
    }
  }

  public static class DeleteEvent extends PropertyFormEvent {

    DeleteEvent(PropertyForm source, Property property) {
      super(source, property);
    }
  }

  public static class CloseEvent extends PropertyFormEvent {

    CloseEvent(PropertyForm source) {
      super(source, null);
    }

  }

}
