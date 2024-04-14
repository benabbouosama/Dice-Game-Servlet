package com.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SecurityFilter extends HttpFilter implements Filter {
    private String loginForm;

    public void init(FilterConfig config) throws ServletException {
        loginForm = config.getServletContext().getInitParameter("inscrConnForms");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;

        // Check if the request is for the login servlet
        if (rq.getServletPath().equals("/ConnexionServlet")) {
            // On récupère la session
            HttpSession session = rq.getSession();

            // On vérifie si l'authentification a déjà eu lieu
            if (session.getAttribute("onlinePlayer") == null) {
                System.out.println(session.getAttribute("onlinePlayer"));
                // Si non il faut interdir l'accès
                rq.getRequestDispatcher(loginForm).forward(request, response);
                // Fin
                return;
            }
        }
        // Si oui, alors continuer vers la resource suivante dans la chaine
        chain.doFilter(request, response);
    }


    public void destroy() {
        // Cleanup resources, if any
    }
}
