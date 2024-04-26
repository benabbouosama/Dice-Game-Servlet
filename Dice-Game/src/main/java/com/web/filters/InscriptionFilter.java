package com.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bo.Message;
import com.web.helpers.GameContextManagement;

/**
 * Filtre permettant de vérifier les informations saisi dans le formulaire d'inscription
 *
 *
 */

public class InscriptionFilter extends HttpFilter implements Filter {

    public InscriptionFilter() {
        super();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // On récupére les vues qu'on a besoin depuis le context
        String inscriptionForm = (String) getServletContext().getInitParameter("inscrConnForms");
        // On récupére les données envoyées dans le formulaire
        String login = (String) request.getParameter("loginInscr");
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String password = (String) request.getParameter("passwordInscr");

        // On vérifie que les données sont bien envoyés dans la requete
        if (nom == null || prenom == null || login == null || password == null ) {
            getServletContext().getRequestDispatcher(inscriptionForm).forward(request, response);
            return;
        }

        GameContextManagement gameContext = GameContextManagement.getInstance(getServletContext());
        // On instancie la liste que nous utiliserons pour stocker les messages
        // à passer à la vue
        List<Message> messages = new ArrayList<Message>();

        // On teste si l'utilisateur avec le même Login existe déja
        if(gameContext.getPlayerByLogin(login) != null) {
            // On ajoute un message
            messages.add(new Message("There is already a user with the same login.", Message.WARN));
            // Mettre la liste des messages dans les attributs de la requête
            request.setAttribute("messages", messages);
            // On envoie la vue d'inscription
            getServletContext().getRequestDispatcher(inscriptionForm).forward(request, response);
            return;
        }
        // On ajoute un message de réussite
        messages.add(new Message("User successfully added", Message.INFO));

        request.setAttribute("messages", messages);

        chain.doFilter(request, response);
    }


}
