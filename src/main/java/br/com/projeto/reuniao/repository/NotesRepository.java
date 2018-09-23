package br.com.projeto.reuniao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reuniao.domain.entity.Notes;

/**
 * Created by abraun on 23/11/2017.
 */
@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
}
