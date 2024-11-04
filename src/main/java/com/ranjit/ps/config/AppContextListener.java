package com.ranjit.ps.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;


@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop abandoned connection cleanup thread
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
