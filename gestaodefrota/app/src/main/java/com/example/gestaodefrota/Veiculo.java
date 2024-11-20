package com.example.gestaodefrota

class Veiculo (val id: Int = 0, var marca: String = "", var modelo: String = "") {

    override fun toString(): String {
        return "$id. $marca $modelo'
    }
}
