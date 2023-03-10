package br.com.cwi.api.service;

import br.com.cwi.api.security.domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ValidaValidadeCodigoService {

    int TEMPO_EXPIRACAO_CODIGO_SEGUNDOS = 1800;

    public void validar(Usuario usuario) {
        long diferenca = Duration.between(usuario.getDataEnvioCodigo(), LocalDateTime.now()).getSeconds();

        if(diferenca > TEMPO_EXPIRACAO_CODIGO_SEGUNDOS){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Código expirado, por favor gere um novo código");
        }
    }
}
