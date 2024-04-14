package com.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.bo.Player;
import com.bo.PlayerStatus;
import com.web.helpers.GameContextManagement;

/**
 * Cette servlet gère l'inscription des joueurs
 *
 *
 */

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {

    public InscriptionServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On récupére les vues qu'on a besoin depuis le context
        String loginForm = (String) getServletContext().getInitParameter("inscrConnForms");
        // On récupére les données envoyées dans le formulaire
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String login = (String) request.getParameter("loginInscr");
        String password = (String) request.getParameter("passwordInscr");

        // On instancie un utilisateur avec les même informations envoyé
        Player player = new Player(nom, prenom, login, password, new PlayerStatus(0));

        GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());

        // On ajoute l'utilisateur
        gameContext.insertPlayer(player);

        // On envoie la vue d'authentification
        getServletContext().getRequestDispatcher(loginForm).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
