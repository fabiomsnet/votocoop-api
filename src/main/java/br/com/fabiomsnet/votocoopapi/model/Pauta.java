package br.com.fabiomsnet.votocoopapi.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_PAUTA")
public class Pauta implements Serializable {

    private static final long serialVersionUID = 2600546948209601776L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pauta_id")
    private long id;

    private String nome;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataCriacao;

    private ResultadoVotacao resultadoVotacao;

    public Pauta() {
    }

    public Pauta(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ResultadoVotacao getResultadoVotacao() {
        return resultadoVotacao;
    }

    public void setResultadoVotacao(ResultadoVotacao resultadoVotacao) {
        this.resultadoVotacao = resultadoVotacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pauta)) return false;
        Pauta pauta = (Pauta) o;
        return id == pauta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", resultadoVotacao=" + resultadoVotacao +
                '}';
    }
}
