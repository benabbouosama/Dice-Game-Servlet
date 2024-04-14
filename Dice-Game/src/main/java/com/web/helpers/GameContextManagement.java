package com.web.helpers;

import java.util.List;

import com.bo.Player;

import jakarta.servlet.ServletContext;

public class GameContextManagement {
    private ServletContext context;

    private static GameContextManagement instance;

    private GameContextManagement(ServletContext context) {
        this.context = context;
    }

    synchronized public static final GameContextManagement getInstance(ServletContext context) {
        if (instance == null) {
            instance = new GameContextManagement(context);
        }
        return instance;
    }

    public void insertPlayer(Player player) {
        List<Player> playerList = (List<Player>) context.getAttribute("players");

        playerList.add(player);

    }

    public List<Player> getAllPlayers() {
        return (List<Player>) context.getAttribute("players");

    }

    public Player getPlayerByLogin(String login) {
        List<Player> players = getAllPlayers();
        for (Player it : players) {
            if (it.getLogin().equals(login)) {
                return it;
            }
        }
        return null;
    }


}
