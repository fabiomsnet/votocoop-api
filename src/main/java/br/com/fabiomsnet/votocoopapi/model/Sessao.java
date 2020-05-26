package br.com.fabiomsnet.votocoopapi.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_SESSAO")
public class Sessao implements Serializable {

    private static final long serialVersionUID = 5893090186305051814L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sessao_id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pauta_id", unique = true, nullable = false, updatable = false)
    private Pauta pauta;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataCriacao;

    private Boolean status;

    private Long duracao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sessao)) return false;
        Sessao sessao = (Sessao) o;
        return id == sessao.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
