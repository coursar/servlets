package org.example.framework.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.framework.attribute.ContextAttributes;
import org.example.app.controller.UserController;
import org.example.framework.handler.WebHandler;
import org.example.app.manager.UserManager;

import java.util.Map;

@WebListener
public class ServletContextLoadDestroyListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        // сборка зависимостей
        final UserManager userManager = new UserManager();
        final UserController userController = new UserController(userManager);

        final Map<String, WebHandler> handlers = Map.of(
          "/users.getAll", userController::getAll, // method reference
          "/users.getById", userController::getById
        );

        servletContext.setAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR, handlers);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}
