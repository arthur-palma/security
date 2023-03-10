package br.com.cwi.api.controller.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AlterarSenhaRequest {

    @NotBlank
    String codigoRecuperacao;

    @NotBlank
    String email;

    @NotBlank
    String senhaRedefinida;

}
