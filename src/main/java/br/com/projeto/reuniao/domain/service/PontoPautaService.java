package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.repository.IPontoPautaRepository;

@Service
public class PontoPautaService {
	
	@Autowired
	private IPontoPautaRepository pontoPautaRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PontoPauta> findAllPontoPautas() {
        return this.pontoPautaRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public PontoPauta findPontoPautaById( long id ) {
		
		final PontoPauta pontoPautaSaved = this.pontoPautaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de PontoPauta com id "+ id + " não encontrado." ) );
		return pontoPautaSaved;
    }
	
	/**
	 * 
	 * @param pontoPauta
	 * @return
	 */
	public PontoPauta insertPontoPauta( PontoPauta pontoPauta ) {
    	
		pontoPauta.refreshCreatedAndUpdated();		
		return this.pontoPautaRepository.save( pontoPauta );
	}
	
	/**
	 * 
	 * @param pontoPauta
	 * @return
	 */
	public PontoPauta updatePontoPauta( PontoPauta pontoPauta ) {
		
		this.pontoPautaRepository.findById(pontoPauta.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de PontoPauta com id "+pontoPauta.getId() + " não encontrado." ) );
		pontoPauta.refreshUpdated();
		return this.pontoPautaRepository.saveAndFlush( pontoPauta );
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deletePontoPauta( long id ) {
		
		final PontoPauta pontoPautaSaved = this.pontoPautaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de PontoPauta com id "+ id + " não encontrado." ) );
		
		this.pontoPautaRepository.deleteById( pontoPautaSaved.getId() );
    }
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countPontoPauta() {
		return this.pontoPautaRepository.count();
	}
}
