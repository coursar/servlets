package org.example.framework.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.framework.attribute.ContextAttributes;
import org.example.framework.handler.WebHandler;

import java.io.IOException;
import java.util.Map;

// TODO:
//  1. XML
//  2. Annotations
@WebServlet(value = "/", loadOnStartup = 1)
@Slf4j
// TODO: Servet, ServletRequest, ServletResponse
// TODO: HttpServlet, HttpServletRequest, HttpServletResponse
public class DispatcherServlet extends HttpServlet {
    private Map<String, WebHandler> handlers;

    @Override
    public void init() throws ServletException {
        handlers = (Map<String, WebHandler>) getServletContext().getAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR);
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // TODO:
        //  1. URL - из URI вырезаем контекст
        //  2. Находим нужный контроллер
        //  3. Отправляем ему запрос (или выдаём 404)
        final String path = req.getRequestURI().substring(
                req.getContextPath().length()
        );

        final WebHandler handler = handlers.get(path);
        if (handler == null) {
            resp.setStatus(404);
            resp.getWriter().write("Not Found");
            return;
        }
        handler.handle(req, resp);
    }
}
