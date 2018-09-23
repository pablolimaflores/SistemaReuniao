package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Notes;
import br.com.projeto.reuniao.repository.NotesRepository;

/**
 * 
 * @author Pablo
 *
 */
@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;
    
    /**
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List<Notes> findAll() {
        return this.notesRepository.findAll();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Notes findById(Long id) {
        return this.notesRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException( "Registro de Notes com id "+ id + " não encontrado." ) );
    }
    
    /**
     * 
     * @param notes
     * @return
     */
    public Notes saveNotes(Notes notes) {
        return this.notesRepository.save(notes);
    }
    
    /**
     * 
     * @param id
     */
	public void deleteNotes( Long id ) {
		
		final Notes notesSaved = this.notesRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Notes com id "+ id + " não encontrado." ) );
		
		this.notesRepository.deleteById( notesSaved.getId() );
    }
}
