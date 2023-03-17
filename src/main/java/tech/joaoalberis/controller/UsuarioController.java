package tech.joaoalberis.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.joaoalberis.dto.UsuarioDto;
import tech.joaoalberis.entity.Usuario;
import tech.joaoalberis.service.UsuarioService;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody Usuario user){
        if (usuarioService.existsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse email ja foi registrado!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody UsuarioDto user){
        if (!usuarioService.existsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email ou Senha Invalido!");
        }
        if (!user.getSenha().equals(usuarioService.loginUser(user))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou Senha Invalido!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Login foi bem sucedido!");
    }
}
