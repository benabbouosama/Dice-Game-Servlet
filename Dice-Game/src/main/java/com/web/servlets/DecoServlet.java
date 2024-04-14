package com.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Cette servlet gère la déconnexion des joueur
 *
 *
 */


@WebServlet("/DecoServlet")
public class DecoServlet extends HttpServlet {

    public DecoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On récupére les vues qu'on a besoin depuis le context
        // la vue d'authentification
        String loginForm = (String) getServletContext().getInitParameter("inscrConnForms");

        HttpSession session = request.getSession(false);

        // Invalider la session
        if (session != null) {
            session.invalidate();
        }
        // On redirige vers la vue d'authentification
        getServletContext().getRequestDispatcher(loginForm).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
