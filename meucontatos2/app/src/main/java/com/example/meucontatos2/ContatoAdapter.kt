package com.example.meucontatos2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ContatoAdapter(private val context: Context, private val contatos: ArrayList<Contato>) : BaseAdapter() {

    override fun getCount(): Int {
        return contatos.size
    }

    override fun getItem(position: Int): Any {
        return contatos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val contato = getItem(position) as Contato
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_contato, parent, false)

        val textNome = view.findViewById<TextView>(R.id.textNome)
        val textTelefone = view.findViewById<TextView>(R.id.textTelefone)

        textNome.text = contato.nome
        textTelefone.text = contato.telefone

        return view
    }
}
