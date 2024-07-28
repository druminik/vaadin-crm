package com.example.application.views.list;

import org.springframework.context.annotation.Scope;

import com.example.application.data.Company;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@org.springframework.stereotype.Component
@Scope("prototype")
@PermitAll
@PageTitle("Company | Customer CRM")
@Route(value = "company", layout = MainLayout.class)
public class CompanyView extends VerticalLayout implements HasUrlParameter<String> {

  Grid<Company> grid = new Grid<>(Company.class);
  TextField filterText = new TextField();
  CompanyForm companyForm;

  private CrmService service;

  public CompanyView(CrmService crmService) {
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
    companyForm.setCompany(null);
    companyForm.setVisible(false);
  }

  private Component getContents() {
    var content = new HorizontalLayout(grid, companyForm);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, companyForm);
    content.setClassName("content");
    content.setSizeFull();
    return content;
  }

  private void configureForm() {
    companyForm = new CompanyForm();
    companyForm.setWidth("25em");

    companyForm.addSaveListener(this::saveCompany);
    companyForm.addDeleteListener(this::deleteCompany);
    companyForm.addCloseListener(event -> closeEditor());
  }

  private void saveCompany(CompanyForm.SaveEvent event) {
    service.saveCompany(event.getCompany());
    updateList();
    closeEditor();
  }

  private void deleteCompany(CompanyForm.DeleteEvent event) {
    service.deleteCompany(event.getCompany());
    updateList();
    closeEditor();
  }

  private void configureGrid() {
    grid.addClassNames("contact-grid");
    grid.setSizeFull();
    grid.setColumns("name");
    // grid.addColumn(company -> company.getName()).setHeader("Name");
    grid.getColumns().forEach(col -> col.setAutoWidth(true));

    grid.asSingleSelect().addValueChangeListener(event -> editCompany(event.getValue()));
  }

  public void editCompany(Company company) {
    if (company == null) {
      closeEditor();
    } else {
      companyForm.setCompany(company);
      companyForm.setVisible(true);
      addClassName("editing");
    }
  }

  private HorizontalLayout getToolbar() {
    filterText.setPlaceholder("Filter by name...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(e -> updateList());

    var addCompanyButton = new Button("Add company");
    addCompanyButton.addClickListener(event -> addCompany());

    var toolbar = new HorizontalLayout(filterText, addCompanyButton);
    toolbar.addClassName("toolbar");
    return toolbar;
  }

  private void addCompany() {
    grid.asSingleSelect().clear();
    editCompany(new Company());
  }

  private void updateList() {
    grid.setItems(service.findAllCompanys(filterText.getValue()));
  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    if (parameter == null)
      return;

    Company company;
    try {
      company = service.getCompany(Long.valueOf(parameter));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return;
    }

    if (company != null) {
      this.filterText.setValue(company.getName());
      updateList();
    }
  }

}
