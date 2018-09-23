package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Usuario extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5477437220949869085L;
	
	
	/**
	 * 
	 */
	@NotBlank
    @Column(nullable = false, length = 100)
    private String login;

	/**
     * 
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String senha;
    
    /**
     * 
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
