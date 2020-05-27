package br.com.fabiomsnet.votocoopapi.dto;

import java.io.Serializable;

public class StatusCooperadoDTO implements Serializable {

    private static final long serialVersionUID = -6204755749622238367L;

    public static final String COOPERADO_LIBERADO = "ABLE_TO_VOTE";

    public static final String COOPERADO_BLOQUEADO = "UNABLE_TO_VOTE";

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
