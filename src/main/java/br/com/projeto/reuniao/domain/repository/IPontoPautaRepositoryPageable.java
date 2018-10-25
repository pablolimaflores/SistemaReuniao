package br.com.projeto.reuniao.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.projeto.reuniao.domain.entity.PontoPauta;

public interface IPontoPautaRepositoryPageable extends PagingAndSortingRepository<PontoPauta, Long>{

}
