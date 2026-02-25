package com.example.meu_primeiro_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * Esta classe inicia a aplicação Spring Boot, configurando automaticamente componentes como
 * controladores, serviços, repositórios e segurança com base nas anotações e dependências.
 * Não se comunica diretamente com outras classes, mas serve como ponto de entrada para o framework.
 */
@SpringBootApplication
public class MeuPrimeiroSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuPrimeiroSpringbootApplication.class, args);
	}

}
