package arq.ifsp.DMO1.examplesqlite.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arq.ifsp.DMO1.examplesqlite.R
import arq.ifsp.DMO1.examplesqlite.data.model.MeuDado
import arq.ifsp.DMO1.examplesqlite.databinding.ItemListDadoBinding

class MeuDadoAdapter(private var dataset: List<MeuDado>) : RecyclerView.Adapter<MeuDadoAdapter.ViewHolder>() {

    fun updateDados(dados: List<MeuDado>) {
        dataset = dados
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_dado, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dado = dataset[position]
        holder.binding.textTextoDado.setText(dado.texto)
        holder.binding.textIdDado.setText("[${dado.id}]")
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemListDadoBinding = ItemListDadoBinding.bind(view)
    }
}