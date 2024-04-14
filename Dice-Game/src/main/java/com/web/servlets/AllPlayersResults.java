package com.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.bo.Player;
import com.web.helpers.GameContextManagement;

/**
 * Cette servlet gère le meilleur score des joueurs
 *
 *
 */


@WebServlet("/AllPlayersResults")
public class AllPlayersResults extends HttpServlet {

    public AllPlayersResults() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On récupére les vues qu'on a besoin depuis le context
        String bestScoresPage = (String) getServletContext().getInitParameter("bestScoresPage");

        GameContextManagement contextGame = GameContextManagement.getInstance(getServletContext());
        // On récupére la liste des utilisateurs
        List<Player> appPlayers = contextGame.getAllPlayers();
        // On stocke la liste des utilisateurs dans la requête
        request.setAttribute("appPlayers", appPlayers);
        // On affiche la vue des meilleurs scores
        getServletContext().getRequestDispatcher(bestScoresPage).forward(request, response);

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
