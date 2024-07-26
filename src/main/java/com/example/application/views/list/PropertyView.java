package com.example.application.views.list;

import org.springframework.context.annotation.Scope;

import com.example.application.data.Property;
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
@PageTitle("Property | Customer CRM")
@Route(value = "/property", layout = MainLayout.class)
@RouteAlias(value = "/property")
public class PropertyView extends VerticalLayout {

  Grid<Property> grid = new Grid<>(Property.class);
  TextField filterText = new TextField();
  PropertyForm propertyForm;

  private CrmService service;

  public PropertyView(CrmService crmService) {
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
    propertyForm.setProperty(null);
    propertyForm.setVisible(false);
  }

  private Component getContents() {
    var content = new HorizontalLayout(grid, propertyForm);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, propertyForm);
    content.setClassName("content");
    content.setSizeFull();
    return content;
  }

  private void configureForm() {
    propertyForm = new PropertyForm();
    propertyForm.setWidth("25em");

    propertyForm.addSaveListener(this::saveProperty);
    propertyForm.addDeleteListener(this::deleteProperty);
    propertyForm.addCloseListener(event -> closeEditor());
  }

  private void saveProperty(PropertyForm.SaveEvent event) {
    service.saveProperty(event.getProperty());
    updateList();
    closeEditor();
  }

  private void deleteProperty(PropertyForm.DeleteEvent event) {
    service.deleteProperty(event.getProperty());
    updateList();
    closeEditor();
  }

  private void configureGrid() {
    grid.addClassNames("contact-grid");
    grid.setSizeFull();
    grid.setColumns("name");
    // grid.addColumn(property -> property.getName()).setHeader("Name");
    grid.getColumns().forEach(col -> col.setAutoWidth(true));

    grid.asSingleSelect().addValueChangeListener(event -> editProperty(event.getValue()));
  }

  public void editProperty(Property property) {
    if (property == null) {
      closeEditor();
    } else {
      propertyForm.setProperty(property);
      propertyForm.setVisible(true);
      addClassName("editing");
    }
  }

  private HorizontalLayout getToolbar() {
    filterText.setPlaceholder("Filter by name...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(e -> updateList());

    var addPropertyButton = new Button("Add property");
    addPropertyButton.addClickListener(event -> addProperty());

    var toolbar = new HorizontalLayout(filterText, addPropertyButton);
    toolbar.addClassName("toolbar");
    return toolbar;
  }

  private void addProperty() {
    grid.asSingleSelect().clear();
    editProperty(new Property());
  }

  private void updateList() {
    grid.setItems(service.findAllPropertys(filterText.getValue()));
  }

}
