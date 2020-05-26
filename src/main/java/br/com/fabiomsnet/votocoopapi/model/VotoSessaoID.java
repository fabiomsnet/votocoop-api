package br.com.fabiomsnet.votocoopapi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VotoSessaoID implements Serializable {

    private static final long serialVersionUID = 764293655231936681L;

    @Column(name = "sessao_id")
    private long idSessao;

    @Column(name = "cliente_id")
    private String idCliente;

    public VotoSessaoID() {
    }

    public VotoSessaoID(long idSessao, String idCliente) {
        this.idSessao = idSessao;
        this.idCliente = idCliente;
    }

    public long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(long idSessao) {
        this.idSessao = idSessao;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoSessaoID that = (VotoSessaoID) o;
        return idSessao == that.idSessao &&
                idCliente.equals(that.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSessao, idCliente);
    }
}
