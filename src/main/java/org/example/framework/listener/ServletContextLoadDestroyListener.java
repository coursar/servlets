package org.example.framework.listener;

import com.google.gson.Gson;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.app.controller.UserController;
import org.example.app.manager.UserManager;
import org.example.framework.attribute.ContextAttributes;
import org.example.framework.handler.WebHandler;

import java.util.Map;

@WebListener
public class ServletContextLoadDestroyListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        // сборка зависимостей
        final Gson gson = new Gson();
        final UserManager userManager = new UserManager();
        final UserController userController = new UserController(userManager, gson);

        final Map<String, WebHandler> handlers = Map.of(
                "/users.getAll", userController::getAll, // method reference
                "/users.getById", userController::getById,
                "/users.create", userController::create
        );

        servletContext.setAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR, handlers);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}
