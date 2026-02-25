package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;


@Table(name = "statusOs")
public enum StatusOs {
	ABERTO,
	FECHADO

}
