package br.com.fabiomsnet.votocoopapi.rest;

import br.com.fabiomsnet.votocoopapi.dto.StatusCooperadoDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@RestController
public class BuscaStatusCooperado implements Serializable {

    private static final long serialVersionUID = 5216309304893066869L;

        public StatusCooperadoDTO buscaStatusCooperado(String identificador) {

            RestTemplate restTemplate = new RestTemplate();

            String uri = "https://user-info.herokuapp.com/users/" + identificador;

            return restTemplate.getForObject(uri, StatusCooperadoDTO.class);
        }

}
