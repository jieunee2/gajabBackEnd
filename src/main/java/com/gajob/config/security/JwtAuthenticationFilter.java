package com.gajob.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // Header에서 JWT를 받아옴
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

    // 유효한 Token인지 확인
    if (token != null && jwtTokenProvider.validateToken(token)) {
      // Token이 유효하면 이로부터 유저 정보를 받아옴
      Authentication authentication = jwtTokenProvider.getAuthentication(token);
      // SecurityContext에 Authentication 객체를 저장함
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }
}
