package arq.ifsp.DMO1.examplesqlite.data.database

import android.content.ContentValues
import androidx.core.content.contentValuesOf
import arq.ifsp.DMO1.examplesqlite.data.model.MeuDado

class MeuDAO(private val dbhelper: DatabaseHelper) {


    fun insert(meuDado: MeuDado){
        val values = contentValuesOf().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_TEXTO, meuDado.texto)
        }

        val db = dbhelper.writableDatabase

        db.insert(DatabaseHelper.DATABASE_KEYS.TABLE_NAME,null,values)
    }

    fun getAll(): List<MeuDado>{
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_ID,
            DatabaseHelper.DATABASE_KEYS.COLUMN_TEXTO

        )
        val db = dbhelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.DATABASE_KEYS.TABLE_NAME,columns,null,
            null,
            null,
            null,
            null
        )

        val dados = mutableListOf<MeuDado>()

        cursor.use{
            while(it.moveToNext()){
                dados.add(MeuDado(
                    it.getInt(0),
                    it.getString(0))
                )
                // ordem do select, setado em 0 para garantir que ira manter a sequencia correta
            }
        }

        return dados
    }


    fun  update(meuDado: MeuDado){
        val values = ContentValues().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_TEXTO,meuDado.texto)
        }

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_ID } = ?"
        val whereArgs = arrayOf(meuDado.id.toString())

        // where args precisa ser compativel com a quantidade de colunas da tabela, no caso o
        // prepareStatement tem apenas 1 argumento

        val db = dbhelper.writableDatabase
        db.update(DatabaseHelper.DATABASE_KEYS.COLUMN_TEXTO,
            values,where,whereArgs)

        // caso nao precise de where args so colocar como null nos argumentos do dbHelper
    }
}