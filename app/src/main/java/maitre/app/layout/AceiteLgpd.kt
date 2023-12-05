package maitre.app.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import maitre.app.databinding.ActivityAceiteLgpdBinding

class AceiteLgpd : AppCompatActivity() {

    private val binding by lazy {
        ActivityAceiteLgpdBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lgpdRecusa.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            Toast.makeText(baseContext, "Redirecionando para home", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.lgpdAceite.setOnClickListener {
            val intent = Intent(this, CriarConta::class.java)
            Toast.makeText(baseContext, "Redirecionando para cadastro", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.ibVoltarCadastro.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            Toast.makeText(baseContext, "Redirecionando para login", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}