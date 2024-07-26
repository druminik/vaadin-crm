package com.example.application.views.list;

import org.springframework.context.annotation.Scope;

import com.example.application.data.Address;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.security.PermitAll;

@org.springframework.stereotype.Component
@Scope("prototype")
@PermitAll
@PageTitle("Address | Customer CRM")
@Route(value = "/address", layout = MainLayout.class)
@RouteAlias(value = "/address")
public class AddressView extends VerticalLayout {

  Grid<Address> grid = new Grid<>(Address.class);
  TextField filterText = new TextField();
  AddressForm addressForm;

  private CrmService service;

  public AddressView(CrmService crmService) {
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
    addressForm.setAddress(null);
    addressForm.setVisible(false);
  }

  private Component getContents() {
    var content = new HorizontalLayout(grid, addressForm);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, addressForm);
    content.setClassName("content");
    content.setSizeFull();
    return content;
  }

  private void configureForm() {
    addressForm = new AddressForm();
    addressForm.setWidth("25em");

    addressForm.addSaveListener(this::saveAddress);
    addressForm.addDeleteListener(this::deleteAddress);
    addressForm.addCloseListener(event -> closeEditor());
  }

  private void saveAddress(AddressForm.SaveEvent event) {
    service.saveAddress(event.getAddress());
    updateList();
    closeEditor();
  }

  private void deleteAddress(AddressForm.DeleteEvent event) {
    service.deleteAddress(event.getAddress());
    updateList();
    closeEditor();
  }

  private void configureGrid() {
    grid.addClassNames("contact-grid");
    grid.setSizeFull();
    grid.setColumns("street", "city");
    grid.getColumns().forEach(col -> col.setAutoWidth(true));

    grid.asSingleSelect().addValueChangeListener(event -> editAddress(event.getValue()));
  }

  public void editAddress(Address address) {
    if (address == null) {
      closeEditor();
    } else {
      addressForm.setAddress(address);
      addressForm.setVisible(true);
      addClassName("editing");
    }
  }

  private HorizontalLayout getToolbar() {
    filterText.setPlaceholder("Filter by name...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(e -> updateList());

    var addAddressButton = new Button("Add address");
    addAddressButton.addClickListener(event -> addAddress());

    var toolbar = new HorizontalLayout(filterText, addAddressButton);
    toolbar.addClassName("toolbar");
    return toolbar;
  }

  private void addAddress() {
    grid.asSingleSelect().clear();
    editAddress(new Address());
  }

  private void updateList() {
    grid.setItems(service.findAllAddresss(filterText.getValue()));
  }

}
