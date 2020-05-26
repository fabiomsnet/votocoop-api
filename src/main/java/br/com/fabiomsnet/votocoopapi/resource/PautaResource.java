package br.com.fabiomsnet.votocoopapi.resource;

import br.com.fabiomsnet.votocoopapi.model.Pauta;
import br.com.fabiomsnet.votocoopapi.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pauta")
@Api(value = "API REST Pauta")
@CrossOrigin(origins = "*")
public class PautaResource {

    @Autowired
    PautaService pautaService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna a pauta correspondente ao ID informado na URL.")
    public Pauta pautaPorId(@PathVariable(value = "id") Long idPauta){
        return pautaService.buscarPautaPorId(idPauta);
    }

    @GetMapping
    @ApiOperation(value = "Retorna todas as pautas cadastradas.")
    public List<Pauta> listaPautas(){
        return pautaService.buscarPautas();
    }

    @PostMapping
    @ApiOperation(value = "Cria uma nova pauta a partir do nome informado no body da requisição.")
    public Pauta criarPauta(@RequestBody Pauta pauta){
        return pautaService.criarPauta(pauta);
    }

}
