package br.com.projeto.reuniao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Participante;

@Repository
public interface IParticipanteRepository extends JpaRepository<Participante, Long>{

}
