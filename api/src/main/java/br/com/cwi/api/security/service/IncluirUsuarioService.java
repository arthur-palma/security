package br.com.cwi.api.security.service;

import br.com.cwi.api.controller.request.UsuarioRequest;
import br.com.cwi.api.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.cwi.api.security.mapper.UsuarioMapper.toEntity;
import static br.com.cwi.api.security.mapper.UsuarioMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse incluir(UsuarioRequest request) {

        Usuario usuario = toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setCriadoEm(LocalDateTime.now());

        request.getPermissoes()
                .forEach(p -> usuario.adicionarPermissao(Permissao.builder().nome(p).build()));

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
