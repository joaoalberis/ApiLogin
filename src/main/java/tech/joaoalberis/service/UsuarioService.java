package tech.joaoalberis.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tech.joaoalberis.dto.UsuarioDto;
import tech.joaoalberis.entity.Usuario;
import tech.joaoalberis.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario user) {
        return usuarioRepository.save(user);
    }

    public String loginUser(UsuarioDto user){
        var usuario = new Usuario();
        BeanUtils.copyProperties(user, usuario);
        return usuarioRepository.findSenhaByEmail(usuario.getEmail());
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
