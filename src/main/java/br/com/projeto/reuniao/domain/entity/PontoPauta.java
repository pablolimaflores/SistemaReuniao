package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *Classe que representa os pontos de pauta de uma reunião e seus atributos.
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PontoPauta extends AbstractEntity {		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2875600846014528656L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Integer ordem;	
	
	/**
	 * 
	 */	
	@NotBlank
    @Column(nullable = false, length = 500)
    @Size(min=3, max=500)
	private String descricao;
	
	/**
	 * 
	 */
	@NotNull
	@Column
	private Integer tempo;
	
	@Column(nullable = true, length = 1500)
    @Size(max=1500)
	private String discussao;
	
	/**
     * 
     */
    @NotNull
//    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pessoa_id")
	private Pessoa responsavel;
	
	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Tipo tipo;
	
	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Reuniao reuniao;

	/**
	 * 
	 * @param id
	 * @param ordem
	 * @param descricao
	 * @param tempo
	 * @param discussao
	 * @param responsavel
	 * @param tipo
	 */
	public PontoPauta(Long id, Integer ordem, String descricao, Integer tempo, String discussao, Pessoa responsavel, Tipo tipo) {
		super(id);
		this.ordem = ordem;
		this.descricao = descricao;
		this.tempo = tempo;
		this.discussao = discussao;
		this.responsavel = responsavel;
		this.tipo = tipo;
	}
	
	/**
	 * 	
	 * @param ordem
	 * @param descricao
	 * @param tempo
	 * @param discussao
	 * @param responsavel
	 * @param tipo
	 */
	public PontoPauta(Integer ordem, String descricao, Integer tempo, String discussao, Pessoa responsavel, Tipo tipo) {		
		this.ordem = ordem;
		this.descricao = descricao;
		this.tempo = tempo;
		this.discussao = discussao;
		this.responsavel = responsavel;
		this.tipo = tipo;
	}
	
	public PontoPauta(Long id, Integer ordem, String descricao, Integer tempo, String discussao, Pessoa responsavel, Tipo tipo, Reuniao reuniao) {
		super(id);
		this.ordem = ordem;
		this.descricao = descricao;
		this.tempo = tempo;
		this.discussao = discussao;
		this.responsavel = responsavel;
		this.tipo = tipo;
		this.reuniao = reuniao;
	}
	
	
	public PontoPauta(Integer ordem, String descricao, Integer tempo, String discussao, Pessoa responsavel, Tipo tipo, Reuniao reuniao) {		
		this.ordem = ordem;
		this.descricao = descricao;
		this.tempo = tempo;
		this.discussao = discussao;
		this.responsavel = responsavel;
		this.tipo = tipo;
		this.reuniao = reuniao;
	}
	
	public PontoPauta(Integer ordem, String descricao, Integer tempo, Pessoa responsavel, Tipo tipo, Reuniao reuniao) {		
		this.ordem = ordem;
		this.descricao = descricao;
		this.tempo = tempo;		
		this.responsavel = responsavel;
		this.tipo = tipo;
		this.reuniao = reuniao;
	}
				
}
