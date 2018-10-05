package br.com.projeto.reuniao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.TipoParticipante;

@Repository
public interface ITipoParticipanteRepository extends JpaRepository<TipoParticipante, Long>{

	@Query("SELECT new TipoParticipante(tipoParticipante.id, tipoParticipante.nome, tipoParticipante.descricao) "
			+ "FROM TipoParticipante tipoParticipante "
			+ "WHERE tipoParticipante.nome = :nome")
	Optional<TipoParticipante> findByNome(@Param("nome") String nome);
	
}
