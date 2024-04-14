package com.bo;

/**
 * Représente le joueur dans l'application
 *
 *
 *
 */

public class Player {

    private String nom;

    private String prenom;

    private String login;

    private String password;

    private PlayerStatus playerStatus;

    public Player(String nom, String prenom, String login, String password, PlayerStatus playerStatus) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.playerStatus = playerStatus;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

}