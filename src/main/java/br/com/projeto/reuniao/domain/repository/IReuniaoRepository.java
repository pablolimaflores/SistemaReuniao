package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	Page<Reuniao> findAllExecutedByData(Pageable pageable);
	
	@Query("SELECT New Reuniao(reuniao.id, reuniao.titulo, reuniao.data) FROM Reuniao reuniao WHERE reuniao.data >= current_timestamp()")
	Page<Reuniao> findAllScheduledByData(Pageable pageable);
}
