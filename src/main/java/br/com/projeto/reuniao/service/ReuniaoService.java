package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.repository.IReuniaoRepository;

@Service
public class ReuniaoService {
	
	@Autowired
	private IReuniaoRepository reuniaoRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Reuniao> findAllReuniaos() {
        return this.reuniaoRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Reuniao findReuniaoById( long id ) {
		
		final Reuniao reuniaoSaved = this.reuniaoRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Reuniao com id "+ id + " não encontrado." ) );
		return reuniaoSaved;
    }
	
	/**
	 * 
	 * @param reuniao
	 * @return
	 */
	public Reuniao insertReuniao( Reuniao reuniao ) {
    	reuniao.refreshCreatedAndUpdated();		
		final Reuniao reuniaoSaved = this.reuniaoRepository.save( reuniao );
		return reuniaoSaved;
	}
	
	/**
	 * 
	 * @param reuniao
	 * @return
	 */
	public Reuniao updateReuniao( Reuniao reuniao ) {
		
		Reuniao reuniaoSaved = this.reuniaoRepository.findById(reuniao.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Reuniao com id "+reuniao.getId() + " não encontrado." ) );
		reuniaoSaved.refreshUpdated();
		return reuniaoSaved;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteReuniao( long id ) {
		
		final Reuniao reuniaoSaved = this.reuniaoRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Reuniao com id "+ id + " não encontrado." ) );
		
		this.reuniaoRepository.deleteById( reuniaoSaved.getId() );
    }
}
