package com.devnaut.expensetracker.expensive.filters;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.devnaut.expensetracker.expensive.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class AuthFilter extends OncePerRequestFilter {
  private final UserRepository userRepository;

  public AuthFilter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    //pegar usuario do request
    String auth = request.getHeader("Authorization");
    //pegar da string apenas do length do basic ->
    String newAuthCredentials = auth.substring("Basic".length()).trim();
    byte[] bytesCredentials = Base64.getDecoder().decode(newAuthCredentials);
    //formatando de array de bytes para string
    String userCredentials = new String(bytesCredentials);
    //fazer split na string por : pegando usuario e senha
    String[] credentials = userCredentials.split(":");
    String username = credentials[0];
    String password = credentials[1];
    //fazer validação se existe esse username e senha no meu bd
    if(userRepository.findByLoginAndPassword(username, BCrypt.withDefaults().hashToString(4,password.toCharArray()))) {
      filterChain.doFilter(request, response);
    }
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
  }
}
