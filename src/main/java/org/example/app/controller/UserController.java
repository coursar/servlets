package org.example.app.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.app.dto.UserDTO;
import org.example.app.manager.UserManager;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor // генерирует конструктор только для final non-static полей
public class UserController {
    private final UserManager manager;
    private final Gson gson;

    public void getAll(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        // TODO:
        //  1. req - параметры
        //  2. передать параметры в manager'а
        //  3. записать ответ с помощью Gson
        final List<UserDTO> responseDTO = manager.getAll();
        res.getWriter().write(gson.toJson(responseDTO));
    }

    // TODO: http://localhost:8080?users.getById?id=1
    public void getById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        // TODO:
        //  1. req - параметры
        //  2. передать параметры в manager'а
        //  3. записать ответ с помощью Gson
        final long id = Long.parseLong(req.getParameter("id"));
        final UserDTO responseDTO = manager.getById(id);
        res.getWriter().write(gson.toJson(responseDTO));
    }

    public void create(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final UserDTO responseDTO = manager.create(login);
        res.getWriter().write(gson.toJson(responseDTO));
    }
}
