package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.repository.IPontoPautaRepository;

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
		final PontoPauta pontoPautaSaved = this.pontoPautaRepository.save( pontoPauta );
		return pontoPautaSaved;
	}
	
	/**
	 * 
	 * @param pontoPauta
	 * @return
	 */
	public PontoPauta updatePontoPauta( PontoPauta pontoPauta ) {
		
		PontoPauta pontoPautaSaved = this.pontoPautaRepository.findById(pontoPauta.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de PontoPauta com id "+pontoPauta.getId() + " não encontrado." ) );
		pontoPautaSaved.refreshUpdated();
		return pontoPautaSaved;
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
}