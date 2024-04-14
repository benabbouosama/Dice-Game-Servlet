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

/**
 * Filtre permettant de restreindre l'utilisateur déja authentifié
 * de revenir au formulaire d'inscription/d'authentification
 *
 *
 */
public class BehaviourFilter extends HttpFilter implements Filter {

    public BehaviourFilter() {
        super();

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;

        // On récupere la vue du home depuis le contexte
        String homePage = (String) getServletContext().getInitParameter("homePage");

        HttpSession session = rq.getSession();

        // On vérifie si l'utilisateur existe déja dans la session
        if (session.getAttribute("onlinePlayer") != null) {
            // On envoie la vue du jeu
            getServletContext().getRequestDispatcher(homePage).forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }


}
