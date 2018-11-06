package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Reuniao;

@Repository
public interface IReuniaoRepository extends JpaRepository<Reuniao, Long>{
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@Query("SELECT New Reuniao(reuniao.id, reuniao.titulo, reuniao.data) FROM Reuniao reuniao WHERE reuniao.data < current_timestamp()")
	Page<Reuniao> findAllExecutedByData(Pageable pageable);
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@Query("SELECT New Reuniao(reuniao.id, reuniao.titulo, reuniao.data) FROM Reuniao reuniao WHERE reuniao.data >= current_timestamp()")
	Page<Reuniao> findAllScheduledByData(Pageable pageable);
	
	/**
	 * Médoto que traz, paginado, todos as Reuniões de acordo com o filtro passsado por parâmetro
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new Reuniao(reuniao.id, reuniao.titulo, reuniao.data) "
			+ "FROM Reuniao reuniao "
			+ "WHERE ( "
			+ "lower(reuniao.titulo) like '%'||lower( coalesce(:filter, '') )||'%' OR "			
			+ "coalesce(:filter, '') = '' "
			+ ") ")
	Page<Reuniao> findByFilter(@Param("filter") String filter, Pageable pageable);
}
