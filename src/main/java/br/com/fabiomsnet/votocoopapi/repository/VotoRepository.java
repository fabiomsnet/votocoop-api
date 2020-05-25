package br.com.fabiomsnet.votocoopapi.repository;

import br.com.fabiomsnet.votocoopapi.model.Voto;
import br.com.fabiomsnet.votocoopapi.model.VotoSessaoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VotoRepository extends JpaRepository<Voto, VotoSessaoID> {

    @Query("SELECT count(voto) FROM Voto voto WHERE voto.votoSessaoID.idSessao = :idSessao and voto.voto_cliente = :voto")
    Long countByVotoIdSessao(@Param("idSessao")long idSessao, @Param("voto")Boolean voto);

}
