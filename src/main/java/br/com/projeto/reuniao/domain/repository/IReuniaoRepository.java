package br.com.projeto.reuniao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Reuniao;

@Repository
public interface IReuniaoRepository extends JpaRepository<Reuniao, Long>{

	
	/**
	 * 
	 * @return
	 */
	@Query("SELECT New Reuniao(reuniao.id, reuniao.titulo, reuniao.data) FROM Reuniao reuniao WHERE reuniao.data < current_timestamp()")
	List<Reuniao> findAllExecutedByData();
	
	@Query("SELECT New Reuniao(reuniao.id, reuniao.titulo, reuniao.data) FROM Reuniao reuniao WHERE reuniao.data >= current_timestamp()")
	List<Reuniao> findAllScheduledByData();
}
