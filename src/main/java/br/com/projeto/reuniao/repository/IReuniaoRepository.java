package br.com.projeto.reuniao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Reuniao;

@Repository
public interface IReuniaoRepository extends JpaRepository<Reuniao, Long>{

}
