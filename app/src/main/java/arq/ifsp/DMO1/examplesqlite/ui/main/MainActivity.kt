package arq.ifsp.DMO1.examplesqlite.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import arq.ifsp.DMO1.examplesqlite.databinding.ActivityMainBinding
import arq.ifsp.DMO1.examplesqlite.ui.details.DetailsActivity

class MainActivity : AppCompatActivity(), ItemListDadoClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MeuDadoAdapter
    private lateinit var addResultLaucher: ActivityResultLauncher<Intent>
    private lateinit var updateResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        setupRecyclerView()
        setupObservers()
        setupListeners()
        setupLauncher()
        viewModel.load()
    }


    override fun onResume() {
        super.onResume()
        viewModel.load()
    }


    override fun clickEditItemList(id: Int, texto: String) {
        val mIntent = Intent(this,DetailsActivity::class.java)
        mIntent.putExtra("id",id)
        mIntent.putExtra("texto",texto)
        updateResultLauncher.launch(mIntent)
        // enviando os dados via intent para a details activity
    }

    override fun clickDeleteItemList(id: Int) {
        viewModel.notifyDelete(id)
    }


    private fun setupLauncher() {
        addResultLaucher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                if (it.resultCode == RESULT_OK) {
                    val texto = it.data?.getStringExtra("texto") ?: ""
                    viewModel.addDado(texto)
                }
            }
        )


        updateResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                if(it.resultCode == RESULT_OK){
                    val texto = it.data?.getStringExtra("texto") ?: ""
                    val id = it.data?.getIntExtra("id",-1) ?: -1 // caso o id seja nulo, utiliza o ?: para setar isso
                    viewModel.updateDado(id,texto)
                }
            }
        )
    }

    private fun setupListeners() {
        binding.buttonAdd.setOnClickListener {
            val mIntent = Intent(this, DetailsActivity::class.java)
            addResultLaucher.launch(mIntent)
        }
    }

    private fun setupObservers() {
        viewModel.dados.observe(this, Observer {
            adapter.updateDados(it)
        })
    }

    private fun setupRecyclerView() {
        adapter = MeuDadoAdapter(mutableListOf(), this)
        binding.listDados.adapter = adapter
        binding.listDados.layoutManager = LinearLayoutManager(this)
    }


}