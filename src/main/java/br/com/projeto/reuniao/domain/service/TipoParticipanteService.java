package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.domain.repository.ITipoParticipanteRepository;
import br.com.projeto.reuniao.domain.repository.ITipoParticipanteRepositoryPageable;

@Service
public class TipoParticipanteService {
	
	@Autowired
	private ITipoParticipanteRepository tipoParticipanteRepository;
	
	@Autowired
	private ITipoParticipanteRepositoryPageable iTipoParticipanteRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TipoParticipante> findAllTiposParticipante() {
        return this.tipoParticipanteRepository.findAll();
    }
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<TipoParticipante> findAllTiposParticipantePage(Pageable pageable) {
        return this.iTipoParticipanteRepository.findAll(pageable);
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
    	
		tipoParticipante.setAtivo(true);
		tipoParticipante.refreshCreatedAndUpdated();		
		return this.tipoParticipanteRepository.save( tipoParticipante );		
	}
	
	/**
	 * 
	 * @param tipoParticipante
	 * @return
	 */
	public TipoParticipante updateTipoParticipante( TipoParticipante tipoParticipante ) {
		
		this.tipoParticipanteRepository.findById(tipoParticipante.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de TipoParticipante com id "+tipoParticipante.getId() + " não encontrado." ) );
		
		tipoParticipante.refreshUpdated();
		return this.tipoParticipanteRepository.saveAndFlush( tipoParticipante );
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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public TipoParticipante updateStatusTipoParticipante( long id ) {
		
		TipoParticipante tipoParticipanteSaved = this.tipoParticipanteRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "TipoParticipante não encontrado." ));		 
		
		tipoParticipanteSaved.setAtivo( !tipoParticipanteSaved.getAtivo() );
		
		return this.tipoParticipanteRepository.saveAndFlush( tipoParticipanteSaved );
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countTiposParticipante() {
		return this.tipoParticipanteRepository.count();
	}
}
