package arq.ifsp.DMO1.examplesqlite.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arq.ifsp.DMO1.examplesqlite.R
import arq.ifsp.DMO1.examplesqlite.databinding.ActivityDetailsBinding
import arq.ifsp.DMO1.examplesqlite.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == binding.buttonSave.id) {
            val string = binding.textTexto.text.toString()
            if (string.isNotEmpty()) {
                val mIntent = Intent()
                mIntent.putExtra("texto", string)
                setResult(RESULT_OK, mIntent)
            } else {
                Toast.makeText(this, "Não foi inserido texto.", Toast.LENGTH_SHORT).show()
                setResult(RESULT_CANCELED)
            }
            finish()
        }
    }
}