package arq.ifsp.DMO1.examplesqlite.data.repository

import android.content.Context
import arq.ifsp.DMO1.examplesqlite.data.database.DatabaseHelper
import arq.ifsp.DMO1.examplesqlite.data.database.MeuDAO
import arq.ifsp.DMO1.examplesqlite.data.model.MeuDado

class MeuDadoRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val dao = MeuDAO(dbHelper)

    fun getAllMeusDados(): List<MeuDado> = dao.getAll()

    fun addMeuDado(dado: MeuDado) = dao.insert(dado)
}