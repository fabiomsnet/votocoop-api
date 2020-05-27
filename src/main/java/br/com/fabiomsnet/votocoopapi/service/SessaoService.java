package br.com.fabiomsnet.votocoopapi.service;

import br.com.fabiomsnet.votocoopapi.dto.SessaoDTO;
import br.com.fabiomsnet.votocoopapi.dto.StatusCooperadoDTO;
import br.com.fabiomsnet.votocoopapi.dto.VotoDTO;
import br.com.fabiomsnet.votocoopapi.model.*;
import br.com.fabiomsnet.votocoopapi.model.enums.VotoEnum;
import br.com.fabiomsnet.votocoopapi.repository.SessaoRepository;
import br.com.fabiomsnet.votocoopapi.repository.VotoRepository;
import br.com.fabiomsnet.votocoopapi.rest.BuscaStatusCooperado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    BuscaStatusCooperado buscaStatusCooperado;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static final String TOPICO_KAFKA="votocoop";

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
        ObjectMapper mapper = new ObjectMapper();

        Long votosAFavor = votoRepository.countByVotoIdSessao(sessao.getId(), VotoEnum.AFAVOR.getVoto());
        Long votosContra = votoRepository.countByVotoIdSessao(sessao.getId(), VotoEnum.CONTRA.getVoto());

        Pauta pauta = pautaService.gravarResultadoVotacao(sessao.getPauta().getId(), votosContra, votosAFavor);

        try {
            sendMessage(mapper.writeValueAsString(pauta));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public VotoDTO criarVotoCooperado(VotoDTO voto) throws NotFoundException {

        validarCooperadoSessao(voto);

        votoRepository.persistVoto(voto.getIdSessao(), voto.getIdCliente(), new Date(), voto.getVoto_cliente());

        return voto;
    }

    private void validarCooperadoSessao(VotoDTO voto) throws NotFoundException {
        Sessao sessao = sessaoRepository.findById(voto.getIdSessao()).orElse(null);
        StatusCooperadoDTO statusCooperadoDTO = buscaStatusCooperado.buscaStatusCooperado(voto.getIdCliente());
        if (sessao != null && sessao.getStatus() && statusCooperadoDTO != null
                && StatusCooperadoDTO.COOPERADO_LIBERADO.equals(statusCooperadoDTO.getStatus())) {
            Long.parseLong(voto.getIdCliente());
        } else {
            throw new NotFoundException("");
        }
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(TOPICO_KAFKA, msg);
    }

}
