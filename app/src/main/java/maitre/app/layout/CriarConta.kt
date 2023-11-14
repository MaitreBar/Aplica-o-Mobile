package maitre.app.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import maitre.app.data.Usuario
import maitre.app.databinding.ActivityCriarContaBinding
import maitre.app.utils.Endpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CriarConta : AppCompatActivity() {

    val binding by lazy {
        ActivityCriarContaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvIrLogin.setOnClickListener {
            val intent = Intent(this, CriarConta::class.java)
            Toast.makeText(baseContext, "Redirecionando para tela de cadastro", Toast.LENGTH_SHORT)
            startActivity(intent)
        }

        binding.tvSetaLogin.setOnClickListener {
            val intent = Intent(this, CriarConta::class.java)
            Toast.makeText(baseContext, "Redirecionando para tela de cadastro", Toast.LENGTH_SHORT)
            startActivity(intent)
        }

        binding.btCadastrar.setOnClickListener {
            if (binding.etCadastroSenha.text.toString().equals(binding.etCadastroConfirmarSenha.text.toString())) {
                val usuarioNovo = Usuario(
                    binding.etCadastroNome.text.toString(),
                    binding.etCadastroEmail.text.toString(),
                    binding.etCadastroCpf.text.toString(),
                    LocalDate.parse(
                        binding.etCadastroDtNasc.text.toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    ).toString(),
                    binding.etCadastroCelular.text.toString(),
                    binding.etCadastroRg.text.toString(),
                    binding.etCadastroSenha.text.toString(),
                    null
                )

                cadastrar(usuarioNovo)
            } else {
                binding.etCadastroConfirmarSenha.setError("As senhas não conferem")
            }
        }
    }

    fun cadastrar(u : Usuario){
        val api = Retrofit.Builder()
            .baseUrl("http://44.213.7.88:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoints::class.java)

        api.cadastrar(u).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if(response.isSuccessful) {
                    Toast.makeText(baseContext, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT)
                    Toast.makeText(baseContext, "Redirecionando para tela de login", Toast.LENGTH_SHORT)
                    val intent = Intent(this@CriarConta, Login::class.java)

                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

        }
        )

    }
}