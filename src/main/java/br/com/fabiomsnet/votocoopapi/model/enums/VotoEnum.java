package br.com.fabiomsnet.votocoopapi.model.enums;

import java.io.Serializable;

public enum VotoEnum implements Serializable {
    CONTRA(false),
    AFAVOR(true);

    private Boolean voto;

    VotoEnum(Boolean voto) {
        this.voto = voto;
    }

    public Boolean getVoto() {
        return voto;
    }
}
