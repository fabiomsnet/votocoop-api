package br.com.fabiomsnet.votocoopapi.repository;

import br.com.fabiomsnet.votocoopapi.model.Voto;
import br.com.fabiomsnet.votocoopapi.model.VotoSessaoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;

public interface VotoRepository extends JpaRepository<Voto, VotoSessaoID> {

    @Query("SELECT count(voto) FROM Voto voto WHERE voto.votoSessaoID.idSessao = :idSessao and voto.voto_cliente = :voto")
    Long countByVotoIdSessao(@Param("idSessao")long idSessao, @Param("voto")Boolean voto);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO TB_VOTO(sessao_id, cliente_id, data_voto, voto_cliente) " +
            "VALUES(:idSessao, :idCliente, :data_voto, :voto_cliente)", nativeQuery = true)
    void persistVoto(@Param("idSessao")long idSessao, @Param("idCliente")String idCliente,
                     @Param("data_voto") Date data_voto, @Param("voto_cliente")Boolean voto_cliente);

}
