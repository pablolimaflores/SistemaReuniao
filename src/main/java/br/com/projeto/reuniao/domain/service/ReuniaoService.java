package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.repository.IReuniaoRepository;
import br.com.projeto.reuniao.domain.repository.IReuniaoReportRepository;

@Service
public class ReuniaoService {
	
	@Autowired
	private IReuniaoRepository reuniaoRepository;	
	
	@Autowired
	private IReuniaoReportRepository reuniaoReportRepository;	
	
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
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Reuniao> findAllReunioesPage(Pageable pageable) {
        return this.reuniaoRepository.findByFilter("", pageable);
    }
	
	/**
	 * Metodo para retornar as páginas
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Reuniao> findReunioesByFilter(String filter, Pageable pageable) {
		return this.reuniaoRepository.findByFilter(filter, pageable);
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
    	reuniao.setAtivo(true);
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
	 * 
	 * @param id
	 * @return
	 */
	public Reuniao updateStatusReuniao( long id ) {
		
		Reuniao reuniaoSaved = this.reuniaoRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Reuniao não encontrado." ));		 
		
		reuniaoSaved.setAtivo( !reuniaoSaved.getAtivo() );
		
		return this.reuniaoRepository.saveAndFlush( reuniaoSaved );
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countReunioes() {
		return this.reuniaoRepository.count();
	}
	
	/**
	 * Método para listar as informações da reuniao
	 * @return
	 */
	
	public Page<Reuniao> findAllReuniaoExecutedByData(Pageable pageable){
		
		return this.reuniaoRepository.findAllExecutedByData(pageable);
	}
	
	public Page<Reuniao> findAllReuniaoScheduledByData(Pageable pageable){
		
		return this.reuniaoRepository.findAllScheduledByData(pageable);
	}
	
	/*------------------------------------------------------------------- 
	 *                REPORTS 
	 *-------------------------------------------------------------------*/ 
    
	/**
	 * 
	 * @param reuniaoId
	 * @return
	 */
	public byte[] exportAtaReuniaoToPDF( long reuniaoId ) { 
		return this.reuniaoReportRepository.exportAtaReuniaoToPDF(reuniaoId).toByteArray(); 
	}
	
}
