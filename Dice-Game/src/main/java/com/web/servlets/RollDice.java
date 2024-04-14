package com.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bo.Message;
import com.bo.Player;
import com.bo.PlayerStatus;


@WebServlet("/RollDice")
public class RollDice extends HttpServlet {


    public RollDice() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On récupére la session de l'utilisateur en cours
        HttpSession session = request.getSession();

        // On récupére les vues qu'on a besoin depuis le context
        String resultPage = (String) getServletContext().getInitParameter("ResultPage");
        String homePage = (String) getServletContext().getInitParameter("Home");

        // On récupére de la session, les informations du joueur en cours
        Player onlinePlayer = (Player) session.getAttribute("onlinePlayer");

        // On récupére la liste des tentatives du joueur en cours
        ArrayList<Integer> attempts = onlinePlayer.getPlayerStatus().getAttempts();

        // On teste si on a reçu aucun numéro du dé
        if (request.getParameter("diceNumber") == null) {
            request.getRequestDispatcher(homePage).forward(request, response);
            return;
        }

        // On récupére le numéro du dés que le joueur veut lancé
        int diceNumber = Integer.valueOf(request.getParameter("diceNumber"));

        // On instancie la liste que nous utiliserons pour stocker les messages
        // à passer à la vue
        List<Message> messages = new ArrayList<Message>();

        // On récupére le résultat du lancé
        int resultatLance = dice();
        // On initialise la variable où on stocke le résultat de la partie
        int score = 0;
        // On récupére le meilleur score du joueur
        int bestScore = (int) onlinePlayer.getPlayerStatus().getBestScore();

        // On teste si le joueur a déja lancer le meme dés
        if(attempts.get(diceNumber-1) != 0) {

            // On stock le score et le meilleur score dans la session
            session.setAttribute("score", -1);
            session.setAttribute("bestScore", bestScore);

            // On stock les résultat des dés
            // pour les passer dans la vue de l'affichage du résultat
            request.setAttribute("dice1", attempts.get(0));
            request.setAttribute("dice2", attempts.get(1));
            request.setAttribute("dice3", attempts.get(2));
            // On ajoute un message
            messages.add(new Message("Vous avez lancé le Dé "+ diceNumber +" deux fois", Message.WARN));
            // Mettre la liste des messages dans les attributs de la requête
            request.setAttribute("messages", messages);

            // On réinitialise le status du joueur
            // nous permet de réinitialiser les tentatives
            onlinePlayer.setPlayerStatus(new PlayerStatus(onlinePlayer.getPlayerStatus().getBestScore()));

            // On affiche la vue du resultat
            getServletContext().getRequestDispatcher(resultPage).forward(request, response);
            return;

            // On teste le résultat du premier et deuxième dés
        } else if (diceNumber == 1 && resultatLance > 4 || diceNumber == 2 && resultatLance > 5 ) {
            // On mettre à jour les tentatives du joueur
            attempts.set(diceNumber-1, resultatLance);

            // On stock le score et le meilleur score dans la session
            session.setAttribute("score", score);
            session.setAttribute("bestScore", bestScore);

            // On stock les résultat des dés
            // pour les passer dans la vue de l'affichage du résultat
            request.setAttribute("dice1", attempts.get(0));
            request.setAttribute("dice2", attempts.get(1));
            request.setAttribute("dice3", attempts.get(2));

            // On réinitialise le status du joueur
            // nous permet de réinitialiser les tentatives
            onlinePlayer.setPlayerStatus(new PlayerStatus(onlinePlayer.getPlayerStatus().getBestScore()));
            // On affiche la vue du resultat
            getServletContext().getRequestDispatcher(resultPage).forward(request, response);
            return;
        } else {
            // On mettre à jour les tentatives du joueur
            attempts.set(diceNumber -1,resultatLance);
        }

        // On teste la règle du jeu ( résultat (dé 1) < résultat (dé 2) < résultat (dé 3) )
        for (int i = 0; i < 2; i++) {
            if (attempts.get(i) >= attempts.get(i+1) && attempts.get(i) != 0 && attempts.get(i+1) != 0 ) {
                // On stock le score et le meilleur score dans la session
                session.setAttribute("score", -1);
                session.setAttribute("bestScore", bestScore);

                // On stock les résultat des dés
                // pour les passer dans la vue de l'affichage du résultat
                request.setAttribute("dice1", attempts.get(0));
                request.setAttribute("dice2", attempts.get(1));
                request.setAttribute("dice3", attempts.get(2));

                // On réinitialise le status du joueur
                // nous permet de réinitialiser les tentatives
                onlinePlayer.setPlayerStatus(new PlayerStatus(onlinePlayer.getPlayerStatus().getBestScore()));
                // On affiche la vue du resultat
                getServletContext().getRequestDispatcher(resultPage).forward(request, response);
                return;
            }
        }

        // On teste si le joueur a joué avec tous les dés
        if (!attempts.contains(0)) {
            // On calcule le score totale
            for (int resultatAttempt : attempts ) {
                score = score + resultatAttempt;
            }
            // On stock le score dans la session
            session.setAttribute("score", score );

            // On teste si le nouveau score est le meilleur score du joueur
            if(onlinePlayer.getPlayerStatus().getBestScore() < score) {
                // On mettre à jour le meilleur score du joueur
                onlinePlayer.getPlayerStatus().setBestScore(score);
            }

            // On stock le meilleur score dans la session
            session.setAttribute("bestScore", onlinePlayer.getPlayerStatus().getBestScore());

            // On stock les résultat des dés
            // pour les passer dans la vue de l'affichage du résultat
            request.setAttribute("dice1", attempts.get(0));
            request.setAttribute("dice2", attempts.get(1));
            request.setAttribute("dice3", attempts.get(2));

            // On affiche la vue du resultat
            onlinePlayer.setPlayerStatus(new PlayerStatus(onlinePlayer.getPlayerStatus().getBestScore()));
            getServletContext().getRequestDispatcher(resultPage).forward(request, response);

            return;
        }

        // On affiche la vue du jeu
        getServletContext().getRequestDispatcher(homePage).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // Fonction qui permet de générer un nombre entre 1 et 6
    private int dice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
