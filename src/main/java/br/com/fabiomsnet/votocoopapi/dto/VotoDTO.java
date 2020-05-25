package br.com.fabiomsnet.votocoopapi.dto;

import java.io.Serializable;

public class VotoDTO implements Serializable {

    private static final long serialVersionUID = 8471241542434541629L;

    private long idSessao;

    private long idCliente;

    private Boolean voto_cliente;

    public long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(long idSessao) {
        this.idSessao = idSessao;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public Boolean getVoto_cliente() {
        return voto_cliente;
    }

    public void setVoto_cliente(Boolean voto_cliente) {
        this.voto_cliente = voto_cliente;
    }
}
