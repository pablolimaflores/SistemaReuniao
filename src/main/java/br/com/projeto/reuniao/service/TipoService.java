package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.repository.ITipoRepository;

@Service
public class TipoService {
	
	@Autowired
	private ITipoRepository tipoRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tipo> findAllTipos() {
        return this.tipoRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Tipo findTipoById( long id ) {
		
		final Tipo tipoSaved = this.tipoRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Tipo com id "+ id + " não encontrado." ) );
		return tipoSaved;
    }
	
	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo insertTipo( Tipo tipo ) {
    	tipo.refreshCreatedAndUpdated();		
		final Tipo tipoSaved = this.tipoRepository.save( tipo );
		return tipoSaved;
	}
	
	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo updateTipo( Tipo tipo ) {
		
		Tipo tipoSaved = this.tipoRepository.findById(tipo.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Tipo com id "+tipo.getId() + " não encontrado." ) );
		tipoSaved.refreshUpdated();
		return tipoSaved;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteTipo( long id ) {
		
		final Tipo tipoSaved = this.tipoRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Tipo com id "+ id + " não encontrado." ) );
		
		this.tipoRepository.deleteById( tipoSaved.getId() );
    }
}
