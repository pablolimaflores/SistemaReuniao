package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Pessoa;

@Repository
public interface IPessoaRepositoryPageable extends PagingAndSortingRepository<Pessoa, Long>{

}
