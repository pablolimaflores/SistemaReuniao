package br.com.projeto.reuniao.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.TipoParticipante;

@Repository
public interface ITipoParticipanteRepository extends JpaRepository<TipoParticipante, Long>{

	/**
	 * Método que retorna o TipoParticipante espscífico pelo seu nome (que é único)
	 * @param nome
	 * @return
	 */
	@Query("SELECT new TipoParticipante(tipoParticipante.id, tipoParticipante.nome, tipoParticipante.descricao) "
			+ "FROM TipoParticipante tipoParticipante "
			+ "WHERE tipoParticipante.nome = :nome")
	Optional<TipoParticipante> findByNome(@Param("nome") String nome);
	
	/**
	 * Médoto que traz, paginado, todos os Tipos de acordo com o filtro passsado por parâmetro
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new TipoParticipante(tipoParticipante.id, tipoParticipante.nome, tipoParticipante.descricao) "
			+ "FROM TipoParticipante tipoParticipante "
			+ "WHERE ( "
			+ "lower(tipoParticipante.nome) like '%'||lower( coalesce(:filter, '') )||'%' OR "
			+ "lower(tipoParticipante.descricao) like '%'||lower( coalesce(:filter, '') )||'%' OR "			
			+ "coalesce(:filter, '') = '' "
			+ ") ")
	Page<TipoParticipante> findByFilter(@Param("filter") String filter, Pageable pageable);
	
}
