package br.com.fabiomsnet.votocoopapi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_VOTO")
public class Voto {

    @EmbeddedId
    private VotoSessaoID votoSessaoID;

    @ManyToOne
    @JoinColumn(name = "sessao_id", insertable = false, updatable = false)
    private Sessao sessao;

    @Temporal(TemporalType.DATE)
    private Date data_voto;

    private Boolean voto_cliente;

    public VotoSessaoID getVotoSessaoID() {
        return votoSessaoID;
    }

    public void setVotoSessaoID(VotoSessaoID votoSessaoID) {
        this.votoSessaoID = votoSessaoID;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Date getData_voto() {
        return data_voto;
    }

    public void setData_voto(Date data_voto) {
        this.data_voto = data_voto;
    }

    public Boolean getVoto_cliente() {
        return voto_cliente;
    }

    public void setVoto_cliente(Boolean voto_cliente) {
        this.voto_cliente = voto_cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return votoSessaoID.equals(voto.votoSessaoID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(votoSessaoID);
    }
}
