package com.ranjit.ps.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop abandoned connection cleanup thread
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
