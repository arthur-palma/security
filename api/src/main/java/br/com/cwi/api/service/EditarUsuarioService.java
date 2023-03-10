package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.EditarRequest;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public void editar(EditarRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();

        if(request.getNome() != null)
            usuario.setNome(request.getNome());
        if(request.getTelefone() != null)
            usuario.setTelefone(request.getTelefone());
        if(request.getFotoUrl() != null )
            usuario.setFotoUrl(request.getFotoUrl());

        usuarioRepository.save(usuario);
    }
}
