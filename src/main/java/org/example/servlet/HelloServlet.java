package org.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// TODO:
//  1. XML
//  2. Annotations
@WebServlet("/hi")

// TODO: Servet, ServletRequest, ServletResponse
// TODO: HttpServlet, HttpServletRequest, HttpServletResponse
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello");
    }
}
