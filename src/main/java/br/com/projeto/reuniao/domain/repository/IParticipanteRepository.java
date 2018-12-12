package br.com.projeto.reuniao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Participante;

@Repository
public interface IParticipanteRepository extends JpaRepository<Participante, Long>{

	@Query("SELECT new Participante(participante.id, participante.compareceu, participante.pessoa, participante.reuniao, "
			+ "participante.tipoParticipante) "
			+ "FROM Participante participante "
			+ "WHERE participante.reuniao.id = :idReuniao "
			+ "ORDER BY participante.pessoa.nome")
	List<Participante> listByReuniaoId(@Param("idReuniao") Long idReuniao);
	
}
