package org.example.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.app.manager.UserManager;
import org.example.framework.attribute.RequestAttributes;
import org.example.framework.security.Authentication;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor // генерирует конструктор только для final non-static полей
public class UserController {
    private final UserManager manager;

    @RequestMapping("/users.getAll")
    public void getAll(final HttpServletRequest req, final HttpServletResponse res, @RequestAttribute(RequestAttributes.AUTHENTICATION_ATTR) final Authentication authentication) throws ServletException, IOException {
        res.getWriter().write("getAll");
    }

    @RequestMapping("/users.getById")
    public void getById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().write("getById");
    }
}
