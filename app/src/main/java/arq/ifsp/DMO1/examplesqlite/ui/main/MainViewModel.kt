package arq.ifsp.DMO1.examplesqlite.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arq.ifsp.DMO1.examplesqlite.data.model.MeuDado
import arq.ifsp.DMO1.examplesqlite.data.repository.MeuDadoRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // sempre passar como parametro Application invéz de context, por conta que context pode resultar em problemas
    // aplication é o contexto geral do aplicativo e não o da activity atual

    private  var repository: MeuDadoRepository


    private val _dados = MutableLiveData<List<MeuDado>>()
    val dados: LiveData<List<MeuDado>>
        get() = _dados

    init {
        repository = MeuDadoRepository(application)
    }

    fun load(){
        _dados.value = repository.getAllMeusDados()
        // recupera os dados caso existam qndo reabre o app
    }

    fun addDado(texto: String) {
        repository.addMeuDado(MeuDado(-1,texto))
        _dados.value = repository.getAllMeusDados()
    }
}