package br.com.projeto.reuniao.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {

    ADMINISTRADOR("Administrador"), 	// 0
    USUARIO("Usuário");					// 1    
    
    private String texto;
    
    Perfil(String texto) {
    	this.setTexto(texto);
    }    
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        return this.name();
    }

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}