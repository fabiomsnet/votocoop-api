package br.com.fabiomsnet.votocoopapi.dto;

import java.io.Serializable;

public class SessaoDTO implements Serializable {

    private static final long serialVersionUID = -7972826492815316918L;

    private long idPauta;

    private long duracao;

    public long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(long idPauta) {
        this.idPauta = idPauta;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }
}
