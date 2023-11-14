package maitre.app.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import maitre.app.data.Usuario
import maitre.app.databinding.ActivityLoginBinding
import maitre.app.utils.Endpoints
import maitre.app.utils.Sessao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {

    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLogar.setOnClickListener {
            logar()
        }

        binding.tvIrCadastro.setOnClickListener {
            val intent = Intent(this, CriarConta::class.java)
            Toast.makeText(baseContext, "Redirecionando para tela de cadastro", Toast.LENGTH_SHORT)
            startActivity(intent)
        }

        binding.tvSetaHome.setOnClickListener {
            val intent = Intent(this, CriarConta::class.java)
            startActivity(intent)
        }
    }

    fun logar() {
        val api = Retrofit.Builder()
            .baseUrl("http://44.213.7.88:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoints::class.java)

        api.getLogin(binding.etEmail.text.toString(), binding.etSenha.text.toString()).enqueue(object :
            Callback<Usuario> {
            override fun onResponse(
                call: Call<Usuario>,
                response: Response<Usuario>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(baseContext, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show()
                    Sessao.usuario = response.body()!!
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}