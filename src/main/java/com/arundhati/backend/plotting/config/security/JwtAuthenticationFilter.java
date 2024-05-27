package com.arundhati.backend.plotting.config.security;

import com.arundhati.backend.plotting.models.UserDetails;
import com.arundhati.backend.plotting.service.UserDetailsService;
import com.arundhati.backend.plotting.service.configuration.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
@Order(2)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String[] ALLOWED_PATHS = {"/v1/internal/auth/token"};
    @Autowired
    private JwtService jwtService;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization
        log.info("Security Filter Invoked...");
        String requestHeader = request.getHeader("Authorization");
        if(requestHeader == null) requestHeader = request.getHeaders("Authorization").nextElement();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            requestHeader = headers.nextElement();
            System.out.println("Authorization Header: " + requestHeader);
        }
        //Bearer 2352345235sdfrsfgsdfsdf
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
            try {
                username = this.jwtService.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                log.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                log.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                log.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            log.info("Invalid Header Value !! ");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.findByUserNameActive(username);//todo
            Boolean validateToken = this.jwtService.validateToken(token, userDetails);
            if (validateToken) {
                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.info("Validation fails !!");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        for (String allowedPath : ALLOWED_PATHS) {
            if (path.startsWith(allowedPath)) {
                return true; // bypass filtering for allowed paths
            }
        }
        return false;
    }
}