package com.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bo.Message;
import com.bo.Player;
import com.web.helpers.GameContextManagement;

/**
 * Filtre permettant de vérifier les informations saisi dans le formulaire d'authentification
 *
 *
 */

public class ConnexionFilter extends HttpFilter implements Filter {

    public ConnexionFilter() {
        super();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // On récupére les vues qu'on a besoin depuis le context
        String loginForm = (String) getServletContext().getInitParameter("inscrConnForms");

        // On récupére les données envoyées dans le formulaire
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");

        // On vérifie que les données sont bien envoyés dans la requete
        if (login == null && password == null ) {
            getServletContext().getRequestDispatcher(loginForm).forward(request, response);
            return;
        }

        // On initialise une variable qui détérmine l'état de l'authentification
        boolean loginFailed = true;

        GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());
        // On instancie la liste que nous utiliserons pour stocker les messages
        // à passer à la vue
        List<Message> messages = new ArrayList<Message>();

        // On Récupère l'utilisateur avec le même Login
        Player player = gameContext.getPlayerByLogin(login);
        // On teste si l'utilisateur avec le même Login existe
        if ( player != null) {
            if (player.getPassword().equals(password)){
                loginFailed = false;
            }

        }
        // On teste si l'authentification a échoué
        if (loginFailed) {
            // On ajoute un message
            messages.add(new Message("Aucun utilisateur n'est disponible avec ces informations", Message.WARN));
            // Mettre la liste des messages dans les attributs de la requête
            request.setAttribute("messages", messages);

            // On envoie la vue d'authentification
            getServletContext().getRequestDispatcher(loginForm).forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }


}
