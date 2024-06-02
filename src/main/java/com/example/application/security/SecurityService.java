package com.example.application.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class SecurityService {

  private final AuthenticationContext authenticationContext;

  public SecurityService(AuthenticationContext authenticationContext) {
    this.authenticationContext = authenticationContext;
  }

  public UserDetails getAuthentiatedUser() {
    return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
  }

  public void logout() {
    authenticationContext.logout();
  }
}
