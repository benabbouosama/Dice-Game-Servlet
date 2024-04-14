package com.web.listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bo.Player;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

    public ApplicationInitializer() {

    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();



        List<Player> playerList = Collections.synchronizedList(new ArrayList<Player>());

        ctx.setAttribute("players", playerList);


    }

}
