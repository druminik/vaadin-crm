package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.list.CompanyView;
import com.example.application.views.list.ContactView;
import com.example.application.views.list.PropertyView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

  private final SecurityService securityService;

  public MainLayout(SecurityService securityService) {
    this.securityService = securityService;
    createHeader();
    createDrawer();
  }

  private void createHeader() {
    H1 logo = new H1("Vaadin CRM");
    logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

    String username = securityService.getAuthentiatedUser().getUsername();
    Button logoutButton = new Button("Log out " + username, e -> securityService.logout());

    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logoutButton);

    header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    header.expand(logo);
    header.setWidthFull();
    header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);

    addToNavbar(header);
  }

  private void createDrawer() {
    addToDrawer(new VerticalLayout(new RouterLink("Contacts", ContactView.class)));
    addToDrawer(new VerticalLayout(new RouterLink("Companies", CompanyView.class)));
    addToDrawer(new VerticalLayout(new RouterLink("Properties", PropertyView.class)));
  }
}
