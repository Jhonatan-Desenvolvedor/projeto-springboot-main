package com.example.meu_primeiro_springboot.model;


import jakarta.persistence.*;

/**
 * Entidade JPA representando um usuário no banco de dados.
 * Mapeia para a tabela "usuarios" e é usada para autenticação e registro.
 * Manipulada pelos serviços de usuário e detalhes de usuário para operações de segurança.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo; // ADMINISTRADOR, FUNCIONARIO


    public Usuario() {}
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

    


}
