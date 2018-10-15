package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Tipo;

@Repository
public interface ITipoRepositoryPageable extends PagingAndSortingRepository<Tipo, Long>{

}
