package com.solumesl.aims.security.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.solumesl.aims.saas.adapter.exception.TokenNotFoundException;
import com.solumesl.aims.saas.adapter.util.SolumSaasUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BearerTokenFilter  extends AbstractAuthenticationProcessingFilter  {
	public BearerTokenFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

        String token = request.getHeader(AUTHORIZATION);
        if(SolumSaasUtil.isEmpty(token)) {
        	throw new TokenNotFoundException("Token Missing in Header");
        }
        token = StringUtils.removeStart(token, "Bearer").trim();
      
        Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(token, token);
        return getAuthenticationManager().authenticate(requestAuthentication);

	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		 chain.doFilter(request, response);
	}
}
