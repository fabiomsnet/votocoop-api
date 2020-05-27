package br.com.fabiomsnet.votocoopapi.model;

import java.io.Serializable;

public class ResultadoVotacao implements Serializable {

    private static final long serialVersionUID = 3164716729643011250L;

    private Long votosContra;

    private Long votosAFavor;

    public ResultadoVotacao() {
    }

    public ResultadoVotacao(Long votosContra, Long votosAFavor) {
        this.votosContra = votosContra;
        this.votosAFavor = votosAFavor;
    }

    public Long getVotosContra() {
        return votosContra;
    }

    public void setVotosContra(Long votosContra) {
        this.votosContra = votosContra;
    }

    public Long getVotosAFavor() {
        return votosAFavor;
    }

    public void setVotosAFavor(Long votosAFavor) {
        this.votosAFavor = votosAFavor;
    }

    @Override
    public String toString() {
        return "{" +
                "votosContra=" + votosContra +
                ", votosAFavor=" + votosAFavor +
                '}';
    }
}
