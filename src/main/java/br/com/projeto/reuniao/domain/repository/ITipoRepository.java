package br.com.projeto.reuniao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Tipo;

@Repository
public interface ITipoRepository extends JpaRepository<Tipo, Long>{

	@Query("SELECT new Tipo(tipo.id, tipo.nome, tipo.descricao, tipo.debate) "
			+ "FROM Tipo tipo "
			+ "WHERE tipo.nome = :nome")
	Optional<Tipo> findByNome(@Param("nome") String nome);
	
}
