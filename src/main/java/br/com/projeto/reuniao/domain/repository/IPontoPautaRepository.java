package br.com.projeto.reuniao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.PontoPauta;

@Repository
public interface IPontoPautaRepository extends JpaRepository<PontoPauta, Long>{

	@Query("SELECT new PontoPauta(pontoPauta.id, pontoPauta.ordem, pontoPauta.descricao, pontoPauta.tempo, pontoPauta.discussao, "
			+ "pontoPauta.responsavel, pontoPauta.tipo, pontoPauta.reuniao) "
			+ "FROM PontoPauta pontoPauta "
			+ "WHERE pontoPauta.reuniao.id = :idReuniao")
	List<PontoPauta> listByReuniaoId(@Param("idReuniao") Long idReuniao);
	
}
