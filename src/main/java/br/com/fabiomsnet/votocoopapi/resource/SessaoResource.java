package br.com.fabiomsnet.votocoopapi.resource;

import br.com.fabiomsnet.votocoopapi.dto.VotoDTO;
import br.com.fabiomsnet.votocoopapi.model.Sessao;
import br.com.fabiomsnet.votocoopapi.model.Voto;
import br.com.fabiomsnet.votocoopapi.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/sessao")
@Api(value = "API REST Sessão")
@CrossOrigin(origins = "*")
public class SessaoResource {

    @Autowired
    SessaoService sessaoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna a sessão correspondente ao ID informado.")
    public Sessao sessaoPorId(@PathVariable(value = "id") Long idSessao){
        return sessaoService.buscarSessaoPorId(idSessao);
    }

    @GetMapping
    @ApiOperation(value = "Retorna todas as sessões cadastradas.")
    public List<Sessao> listaSessoes(){
        return sessaoService.buscarPautas();
    }

    @PostMapping
    @ApiOperation(value = "Cria uma nova sessão")
    public Sessao criarSessao(@RequestBody Sessao sessao){
        return sessaoService.criarSessao(sessao);
    }

    @PostMapping("/voto")
    @ApiOperation(value = "Grava votação do cooperado")
    public VotoDTO votoCooperado(@RequestBody VotoDTO voto){
        return sessaoService.criarVotoCooperado(voto);
    }

}
