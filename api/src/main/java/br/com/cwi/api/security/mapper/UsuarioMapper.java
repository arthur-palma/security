package br.com.cwi.api.security.mapper;

import br.com.cwi.api.controller.request.UsuarioRequest;
import br.com.cwi.api.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.security.domain.Usuario;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setTelefone(request.getTelefone());
        entity.setFotoUrl(request.getFotoUrl());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder()
                .nome(entity.getNome())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .fotoUrl(entity.getFotoUrl())
                .criadoEm(entity.getCriadoEm())
                .permissoes(buildPermissoesResponse(entity.getPermissoes()))
                .build();
    }

    private static List<String> buildPermissoesResponse(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(Permissao::getNome)
                .collect(toList());
    }
}
