package maitre.app.layout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import maitre.app.R
import maitre.app.data.Usuario
import maitre.app.databinding.ActivityLoginBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Sessao.urlApi = getString(R.string.url_api)

        // Read login data
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (savedUsername != null && savedPassword != null) {
            loginSalvo(savedUsername, savedPassword)
        }

        binding.btLogar.setOnClickListener {
                logar()
        }

        binding.tvIrLgpd.setOnClickListener {
            val intent = Intent(this, AceiteLgpd::class.java)
            Toast.makeText(baseContext, "Redirecionando para termo LGPD", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.tvSetaHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun logar() {
        NetworkUtils.getRetrofitInstance(Sessao.urlApi)
            .getLogin(binding.etEmail.text.toString(), binding.etSenha.text.toString()).enqueue(object : Callback<Usuario> {
                override fun onResponse(
                    call: Call<Usuario>,
                    response: Response<Usuario>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(baseContext, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show()
                        Sessao.usuario = response.body()!!
                        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("username", binding.etEmail.text.toString())
                        editor.putString("password", binding.etSenha.text.toString())
                        editor.apply()
                        val intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun loginSalvo(email : String, senha: String) {
        NetworkUtils.getRetrofitInstance(Sessao.urlApi)
            .getLogin(email, senha).enqueue(object : Callback<Usuario> {
                override fun onResponse(
                    call: Call<Usuario>,
                    response: Response<Usuario>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(baseContext, "Usuário carregado com sucesso", Toast.LENGTH_SHORT).show()
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