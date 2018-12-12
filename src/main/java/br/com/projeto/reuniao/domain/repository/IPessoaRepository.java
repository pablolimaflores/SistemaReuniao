package br.com.projeto.reuniao.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Pessoa;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{

	Pessoa findByEmail(String email);
	
	/**
	 * Médoto que traz, paginado, todos as Pessoas de acordo com o filtro passsado por parâmetro
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new Pessoa(pessoa.id, pessoa.nome, pessoa.email, pessoa.telefone, pessoa.celular) "
			+ "FROM Pessoa pessoa "
			+ "WHERE ( "
			+ "lower(pessoa.nome) like '%'||lower( coalesce(:filter, '') )||'%' OR "
			+ "lower(pessoa.email) like '%'||lower( coalesce(:filter, '') )||'%' OR "
			+ "pessoa.telefone like '%'||coalesce(:filter, '')||'%' OR "
			+ "pessoa.celular like '%'||coalesce(:filter, '')||'%' OR "
			+ "coalesce(:filter, '') = '' "
			+ ") ")
	Page<Pessoa> findByFilter(@Param("filter") String filter, Pageable pageable);
	
	@Query("SELECT new Pessoa(pessoa.id, pessoa.nome, pessoa.email, pessoa.telefone, pessoa.celular) "
			+ "FROM Pessoa pessoa "
			+ "WHERE pessoa.id NOT IN ( "
			+ "	SELECT p.pessoa.id FROM Participante p "
			+ "	WHERE p.reuniao.id = :idReuniao "
			+ ") ")
	List<Pessoa> findNotParticipanteByReuniaoId(@Param("idReuniao") Long idReuniao);
}
