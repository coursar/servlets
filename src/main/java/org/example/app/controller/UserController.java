package org.example.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.app.dto.UserRequestDTO;
import org.example.app.dto.UserResponseDTO;
import org.example.app.manager.UserManager;
import org.example.framework.security.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // генерирует конструктор только для final non-static полей
public class UserController {
    private final UserManager manager;

    @GetMapping("/users")
    public List<UserResponseDTO> getAll(
            @RequestAttribute final Authentication authentication
    ) {
        final List<UserResponseDTO> responseDTO = manager.getAll();
        return responseDTO;
    }

    // TODO: http://localhost:8080/users/1
    @GetMapping("/users/{id}")
    public UserResponseDTO getById(
            @RequestAttribute final Authentication authentication,
            @PathVariable final long id
    ) {
        final UserResponseDTO responseDTO = manager.getById(id);
        return responseDTO;
    }

    @PostMapping("/users")
    public UserResponseDTO create(
            @RequestAttribute final Authentication authentication,
            @RequestBody final UserRequestDTO requestDTO
    ) {
        final UserResponseDTO responseDTO = manager.create(requestDTO);
        return responseDTO;
    }

    @PutMapping("/users")
    public UserResponseDTO update(
            @RequestAttribute final Authentication authentication,
            @RequestBody final UserRequestDTO requestDTO
    ) {
        final UserResponseDTO responseDTO = manager.update(requestDTO);
        return responseDTO;
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(
            @RequestAttribute final Authentication authentication,
            @PathVariable final long id
    ) {
        manager.deleteById(id);
    }
}
