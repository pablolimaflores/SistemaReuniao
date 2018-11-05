package br.com.projeto.reuniao.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Tipo;

@Repository
public interface ITipoRepository extends JpaRepository<Tipo, Long>{

	/**
	 * Método que retorna o Tipo espscífico pelo seu nome (que é único)
	 * @param nome
	 * @return
	 */
	@Query("SELECT new Tipo(tipo.id, tipo.nome, tipo.descricao, tipo.debate) "
			+ "FROM Tipo tipo "
			+ "WHERE tipo.nome = :nome")
	Optional<Tipo> findByNome(@Param("nome") String nome);
	
	/**
	 * Médoto que traz, paginado, todos os Tipos de acordo com o filtro passsado por parâmetro
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new Tipo(tipo.id, tipo.nome, tipo.descricao, tipo.debate) "
			+ "FROM Tipo tipo "
			+ "WHERE ( "
			+ "lower(tipo.nome) like '%'||lower( coalesce(:filter, '') )||'%' OR "
			+ "lower(tipo.descricao) like '%'||lower( coalesce(:filter, '') )||'%' OR "			
			+ "coalesce(:filter, '') = '' "
			+ ") ")
	Page<Tipo> findByFilter(@Param("filter") String filter, Pageable pageable);
	
}
