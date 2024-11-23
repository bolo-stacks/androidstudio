package com.example.meucontatos2

import java.io.Serializable

data class Contato(
    val nome: String,
    val telefone: String,
    val email: String,
    val endereco: String
) : Serializable
