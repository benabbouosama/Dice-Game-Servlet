package com.web.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


import com.bo.Player;
import com.web.helpers.GameContextManagement;

/**
 * Cette servlet gère l'authentification des joueurs
 *
 *
 */

@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {

    public ConnexionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On récupére les vues qu'on a besoin depuis le context
        String homePage = (String) getServletContext().getInitParameter("Home");
        // On récupére les données envoyées dans le formulaire
        String login = request.getParameter("login");

        GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());
        // On récupére l'utilisateur avec le même login
        Player onlinePlayer = gameContext.getPlayerByLogin(login);
        // On ajoute l'utilisateur dans la session
        request.getSession().setAttribute("onlinePlayer", onlinePlayer);
        // On envoie la vue du jeu
        getServletContext().getRequestDispatcher(homePage).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
