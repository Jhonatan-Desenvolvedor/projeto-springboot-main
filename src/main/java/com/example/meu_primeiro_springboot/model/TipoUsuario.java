package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;


@Table(name = "tipoUsuario")
public enum TipoUsuario {
    ADMINISTRADOR,
    FUNCIONARIO
}