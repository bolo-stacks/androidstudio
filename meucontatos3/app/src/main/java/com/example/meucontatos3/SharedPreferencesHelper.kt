package com.example.meucontatos3

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferencesHelper {

    private const val PREFS_NAME = "contatos_prefs"
    private const val KEY_CONTATOS = "contatos"

    // Função para obter o SharedPreferences
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Função para adicionar um contato
    fun adicionarContato(context: Context, contato: Contato) {
        val sharedPreferences = getSharedPreferences(context)
        // Obtém os contatos existentes e converte para uma lista mutável
        val contatos = getContatos(context).toMutableList()
        // Adiciona o novo contato
        contatos.add(contato)
        // Converte a lista de contatos para JSON usando Gson
        val contatosJson = Gson().toJson(contatos)
        // Salva a lista de contatos no SharedPreferences
        sharedPreferences.edit().putString(KEY_CONTATOS, contatosJson).apply()
    }

    // Função para obter a lista de contatos
    fun getContatos(context: Context): List<Contato> {
        val sharedPreferences = getSharedPreferences(context)
        // Obtém a string JSON dos contatos ou um array vazio caso não haja contatos
        val contatosJson = sharedPreferences.getString(KEY_CONTATOS, "[]") ?: "[]"
        // Define o tipo de dados que será convertido de JSON para Lista
        val type = object : TypeToken<List<Contato>>() {}.type
        // Converte o JSON de volta para uma lista de objetos Contato
        return Gson().fromJson(contatosJson, type)
    }

    // Função para limpar todos os contatos
    fun limparContatos(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        // Remove os contatos salvos
        sharedPreferences.edit().remove(KEY_CONTATOS).apply()
    }
}
