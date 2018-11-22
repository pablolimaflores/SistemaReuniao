package br.com.projeto.reuniao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaReuniaoApp {

	public static final int MAXROWS = 5;
	
    public static void main(String[] args) {
        SpringApplication.run(SistemaReuniaoApp.class, args);
    }

}
