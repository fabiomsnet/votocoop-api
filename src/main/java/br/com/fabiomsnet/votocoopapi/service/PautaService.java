package br.com.fabiomsnet.votocoopapi.service;

import br.com.fabiomsnet.votocoopapi.model.Pauta;
import br.com.fabiomsnet.votocoopapi.model.ResultadoVotacao;
import br.com.fabiomsnet.votocoopapi.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PautaService {

    @Autowired
    PautaRepository repository;

    public Pauta buscarPautaPorId(Long idSessao){
        return repository.findById(idSessao).orElse(null);
    }

    public List<Pauta> buscarPautas(){
        return repository.findAll();
    }

    public Pauta criarPauta(Pauta pauta){
        pauta.setDataCriacao(new Date());
        return repository.save(pauta);
    }

    public void gravarResultadoVotacao(Long idPauta, Long votosContra, Long votosAFavor){
        Pauta pauta = repository.findById(idPauta).orElse(null);
        if(pauta != null) {
            pauta.setResultadoVotacao(new ResultadoVotacao(votosContra, votosAFavor));
            repository.save(pauta);
        }
    }

}
