package br.com.fabiomsnet.votocoopapi.service;

import br.com.fabiomsnet.votocoopapi.dto.SessaoDTO;
import br.com.fabiomsnet.votocoopapi.dto.VotoDTO;
import br.com.fabiomsnet.votocoopapi.model.*;
import br.com.fabiomsnet.votocoopapi.model.enums.VotoEnum;
import br.com.fabiomsnet.votocoopapi.repository.SessaoRepository;
import br.com.fabiomsnet.votocoopapi.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class SessaoService {

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    PautaService pautaService;

    public Sessao buscarSessaoPorId(Long idSessao){
        return sessaoRepository.findById(idSessao).orElse(null);
    }

    public List<Sessao> buscarPautas(){
        return sessaoRepository.findAll();
    }

    public Sessao criarSessao(SessaoDTO sessaoDTO){

        Sessao sessao = new Sessao();
        sessao.setPauta(pautaService.buscarPautaPorId(sessaoDTO.getIdPauta()));
        sessao.setDataCriacao(new Date());
        sessao.setDuracao(sessaoDTO.getDuracao());
        sessao.setStatus(true);

        Sessao sessaoSalva = sessaoRepository.save(sessao);
        sessaoRepository.flush();
        agendarFechamentoSessao(sessaoSalva);
        return sessaoSalva;
    }

    private void agendarFechamentoSessao(Sessao sessao){
        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                if(sessao != null) {
                    sessao.setStatus(false);
                    sessaoRepository.save(sessao);
                    contabilizaVotosAposSessao(sessao);
                }
            }
        };
        long milissegundos = TimeUnit.MINUTES.toMillis(sessao.getDuracao());
        cronometro.schedule(tarefa, milissegundos);
    }

    private void contabilizaVotosAposSessao(Sessao sessao) {
        Long votosAfavor = votoRepository.countByVotoIdSessao(sessao.getId(), VotoEnum.AFAVOR.getVoto());
        Long votosContra = votoRepository.countByVotoIdSessao(sessao.getId(), VotoEnum.CONTRA.getVoto());

        pautaService.gravarResultadoVotacao(sessao.getPauta().getId(), votosContra, votosAfavor);
    }

    public VotoDTO criarVotoCooperado(VotoDTO voto) throws Exception {
        Sessao sessao = sessaoRepository.findById(voto.getIdSessao()).orElse(null);
        VotoDTO votoSalvo = null;
        if (sessao != null && sessao.getStatus()) {
            Long.parseLong(voto.getIdCliente());
            votoRepository.persistVoto(voto.getIdSessao(), voto.getIdCliente(), new Date(), voto.getVoto_cliente());
            votoSalvo = voto;
        } else {
            throw new Exception();
        }
        return votoSalvo;
    }

}
