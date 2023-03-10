package br.com.cwi.api.service;


import br.com.cwi.api.controller.request.AlterarSenhaRequest;
import br.com.cwi.api.controller.request.GerarCodigoRecuperacaoRequest;
import br.com.cwi.api.repository.UsuarioRepository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class GerenciamentoSenhaService {

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidaValidadeCodigoService validaValidadeCodigoService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public void solicitarCodigo(GerarCodigoRecuperacaoRequest request){
        String email = request.getEmail();
        Usuario usuario = usuarioRepository.findByEmail(email);
        usuario.setCodigoRecuperacao(getCodigoRecuperacaoSenha(usuario.getId()));
        usuario.setDataEnvioCodigo(LocalDateTime.now());

        usuarioRepository.save(usuario);

        String TITULO = "Recuperação de senha";
        String MENSAGEM = "Seu código de recuperação de senha é este: "+usuario.getCodigoRecuperacao();
        enviarEmailService.enviarEmailTexto(usuario.getEmail(), TITULO,MENSAGEM);
    }

    public void alterarSenha(AlterarSenhaRequest request) {
        Usuario usuario = usuarioRepository.findByEmailAndCodigoRecuperacao(request.getEmail(),request.getCodigoRecuperacao());
        validaValidadeCodigoService.validar(usuario);

        usuario.setSenha(passwordEncoder.encode(request.getSenhaRedefinida()));
        usuario.setCodigoRecuperacao(null);
        usuario.setDataEnvioCodigo(null);

        usuarioRepository.save(usuario);
    }

    private String getCodigoRecuperacaoSenha(Long id){
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return  format.format(new Date()) + id;
    }

}
