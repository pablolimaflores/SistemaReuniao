package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Pessoa;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{

}
