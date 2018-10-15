package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.repository.ITipoRepository;
import br.com.projeto.reuniao.domain.repository.ITipoRepositoryPageable;

@Service
public class TipoService {
	
	@Autowired
	private ITipoRepository tipoRepository;
	
	@Autowired
	private ITipoRepositoryPageable iTipoRepositoryPageable;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tipo> findAllTipos() {
        return this.tipoRepository.findAll();
    }
	
	/**
	 * Metodo para retornar as páginas
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Tipo> findAllTiposPage(Pageable pageable) {
        return this.iTipoRepositoryPageable.findAll(pageable);
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
	 * @return
	 */
	@Transactional(readOnly = true)
	public Tipo findTipoByNome( String nome ) {
		
		final Tipo tipoSaved = this.tipoRepository.findByNome( nome )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Tipo com nome "+ nome + " não encontrado." ) );
		return tipoSaved;
    }
	
	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo insertTipo( Tipo tipo ) {
		
		tipo.setAtivo(true);
    	tipo.refreshCreatedAndUpdated();		
		return this.tipoRepository.save( tipo );
	}
	
	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo updateTipo( Tipo tipo ) {
		
		this.tipoRepository.findById(tipo.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Tipo com id "+tipo.getId() + " não encontrado." ) );
		tipo.refreshUpdated();
		return this.tipoRepository.saveAndFlush( tipo );
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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tipo updateStatusTipo( long id ) {
		
		Tipo tipoSaved = this.tipoRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Tipo não encontrado." ));		 
		
		tipoSaved.setAtivo( !tipoSaved.getAtivo() );
		
		return this.tipoRepository.saveAndFlush( tipoSaved );
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countTipos() {
		return this.tipoRepository.count();
	}
}
