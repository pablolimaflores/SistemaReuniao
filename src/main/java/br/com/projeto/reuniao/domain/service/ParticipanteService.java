package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.repository.IParticipanteRepository;

@Service
public class ParticipanteService {
	
	@Autowired
	private IParticipanteRepository participanteRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Participante> findAllParticipantes() {
        return this.participanteRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Participante findParticipanteById( long id ) {
		
		final Participante participanteSaved = this.participanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Participante com id "+ id + " não encontrado." ) );
		return participanteSaved;
    }
	
	/**
	 * 
	 * @param participante
	 * @return
	 */
	public Participante insertParticipante( Participante participante ) {
    	participante.refreshCreatedAndUpdated();		
		final Participante participanteSaved = this.participanteRepository.save( participante );
		return participanteSaved;
	}
	
	/**
	 * 
	 * @param participante
	 * @return
	 */
	public Participante updateParticipante( Participante participante ) {
		
		Participante participanteSaved = this.participanteRepository.findById(participante.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Participante com id "+participante.getId() + " não encontrado." ) );
		participanteSaved.refreshUpdated();
		return participanteSaved;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteParticipante( long id ) {
		
		final Participante participanteSaved = this.participanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Participante com id "+ id + " não encontrado." ) );
		
		this.participanteRepository.deleteById( participanteSaved.getId() );
    }
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countParticipantes() {
		return this.participanteRepository.count();
	}
}
