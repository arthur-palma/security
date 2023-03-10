package br.com.cwi.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private String nome;
    private String email;
    private String telefone;
    private String fotoUrl;
    private LocalDateTime criadoEm;
    private List<String> permissoes;
}
