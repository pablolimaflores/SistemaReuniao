package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.repository.ITipoParticipanteRepository;

@Service
public class TipoParticipanteService {
	
	@Autowired
	private ITipoParticipanteRepository tipoParticipanteRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TipoParticipante> findAllTipoParticipantes() {
        return this.tipoParticipanteRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public TipoParticipante findTipoParticipanteById( long id ) {
		
		final TipoParticipante tipoParticipanteSaved = this.tipoParticipanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de TipoParticipante com id "+ id + " não encontrado." ) );
		return tipoParticipanteSaved;
    }
	
	/**
	 * 
	 * @param tipoParticipante
	 * @return
	 */
	public TipoParticipante insertTipoParticipante( TipoParticipante tipoParticipante ) {
    	tipoParticipante.refreshCreatedAndUpdated();		
		final TipoParticipante tipoParticipanteSaved = this.tipoParticipanteRepository.save( tipoParticipante );
		return tipoParticipanteSaved;
	}
	
	/**
	 * 
	 * @param tipoParticipante
	 * @return
	 */
	public TipoParticipante updateTipoParticipante( TipoParticipante tipoParticipante ) {
		
		TipoParticipante tipoParticipanteSaved = this.tipoParticipanteRepository.findById(tipoParticipante.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de TipoParticipante com id "+tipoParticipante.getId() + " não encontrado." ) );
		tipoParticipanteSaved.refreshUpdated();
		return tipoParticipanteSaved;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteTipoParticipante( long id ) {
		
		final TipoParticipante tipoParticipanteSaved = this.tipoParticipanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de TipoParticipante com id "+ id + " não encontrado." ) );
		
		this.tipoParticipanteRepository.deleteById( tipoParticipanteSaved.getId() );
    }
}
