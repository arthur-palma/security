package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.AlterarSenhaRequest;
import br.com.cwi.api.controller.request.EditarRequest;
import br.com.cwi.api.controller.request.GerarCodigoRecuperacaoRequest;
import br.com.cwi.api.controller.request.UsuarioRequest;
import br.com.cwi.api.controller.response.UsuarioResponse;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.IncluirUsuarioService;
import br.com.cwi.api.service.EditarUsuarioService;
import br.com.cwi.api.service.GerenciamentoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private EditarUsuarioService editarUsuarioService;

    @Autowired
    private GerenciamentoSenhaService gerenciamentoSenhaService;

    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @GetMapping("/me")
    public UsuarioResponse buscar() {
        return buscarUsuarioService.buscar();
    }

    @PutMapping("/editar")
    public void editar(@RequestBody EditarRequest request){
         editarUsuarioService.editar(request);
    }

    @PostMapping("/gerar-codigo-recuperacao")
    public void gerarCodigo(@RequestBody GerarCodigoRecuperacaoRequest request){
        gerenciamentoSenhaService.solicitarCodigo(request);
    }

    @PostMapping("/alterar-senha")
    public void alterarSenha(@RequestBody AlterarSenhaRequest request){
        gerenciamentoSenhaService.alterarSenha(request);
    }





}
