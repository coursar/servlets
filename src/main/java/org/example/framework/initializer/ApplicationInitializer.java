package org.example.framework.initializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// TODO: github.com/coursar/servlets
//  src/main/java/org.example.framework/initializer/ApplicationInitializer
public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("org.example.app");
        servletContext.addListener(new ContextLoaderListener(context));

        final var dispatcherServlet = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(context)
        );
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
