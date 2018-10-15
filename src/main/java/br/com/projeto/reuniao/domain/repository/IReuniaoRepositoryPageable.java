package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Reuniao;

@Repository
public interface IReuniaoRepositoryPageable extends PagingAndSortingRepository<Reuniao, Long>{

}
