package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.repository.IParticipanteRepository;

@Service
public class ParticipanteService {
	
	/**
	 * Injeção de dependência
	 */
	@Autowired
	private IParticipanteRepository participanteRepository;
	
	/**
	 * Método utilizado para listar todos os participantes cadastrados na base de dados.
	 * 
	 * @return 
	 */
	@Transactional(readOnly = true)
	public List<Participante> findAllParticipantes() {
        return this.participanteRepository.findAll();
    }
	
	@Transactional(readOnly = true)
	public Page<Participante> findAllParticipantes(Pageable pageable) {
        return this.participanteRepository.findAll(pageable);
    }
	
	/**
	 * Método utilizado para buscar um registro pelo ID.
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
	 * Método utilizado para buscar um registro pelo ID.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Participante> listParticipanteByReuniaoId( Long idReuniao ) {
		
		return this.participanteRepository.listByReuniaoId(idReuniao);
    }
	
	/**
	 * Método utilizado para inserir um registro na base de dados.
	 * 
	 * @param participante
	 * @return
	 */
	public Participante insertParticipante( Participante participante ) {
    	
		participante.setAtivo(true);
		participante.refreshCreatedAndUpdated();		
		final Participante participanteSaved = this.participanteRepository.save( participante );
		return participanteSaved;
	}
	
	/**
	 * Método para atualizar informações de um registro na base de dados.
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
	 * Método para excluir um registro na base de dados.
	 * 
	 * @param id
	 */
	public void deleteParticipante( long id ) {
		
		final Participante participanteSaved = this.participanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Participante com id "+ id + " não encontrado." ) );
		
		this.participanteRepository.deleteById( participanteSaved.getId() );
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Participante updateStatusParticipante( long id ) {
		
		Participante participanteSaved = this.participanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Participante não encontrado." ));		 
		
		participanteSaved.setAtivo( !participanteSaved.getAtivo() );
		
		return this.participanteRepository.saveAndFlush( participanteSaved );
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countParticipantes() {
		return this.participanteRepository.count();
	}
}
