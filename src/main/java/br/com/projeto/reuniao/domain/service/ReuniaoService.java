package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.repository.IReuniaoRepository;

@Service
public class ReuniaoService {
	
	@Autowired
	private IReuniaoRepository reuniaoRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Reuniao> findAllReunioes() {
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
		return this.reuniaoRepository.save( reuniao );
		
	}
	
	/**
	 * 
	 * @param reuniao
	 * @return
	 */
	public Reuniao updateReuniao( Reuniao reuniao ) {
		
		this.reuniaoRepository.findById(reuniao.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Reuniao com id "+reuniao.getId() + " não encontrado." ) );
		reuniao.refreshUpdated();
		return this.reuniaoRepository.saveAndFlush( reuniao );
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
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countReunioes() {
		return this.reuniaoRepository.count();
	}
}