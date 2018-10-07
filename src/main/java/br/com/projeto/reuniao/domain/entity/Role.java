package br.com.projeto.reuniao.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN("Administrador"),	
    ROLE_USER("Usuário"); 
    
    private String translate;
    
    private Role(String translate) {
    	this.setTranslate(translate);
    }    
       
    public String getTranslate() {
    	return translate;
    }
    
    public void setTranslate(String translate) {
    	this.translate = translate;
    }
    
    @Override
    public String getAuthority() {
        return this.name();
    }

}