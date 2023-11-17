package maitre.app.layout

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.set
import maitre.app.R
import maitre.app.data.Usuario
import maitre.app.databinding.FragmentAlterarPerfilBinding
import maitre.app.utils.Endpoints
import maitre.app.utils.Sessao.usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AlterarPerfil : Fragment() {

    val binding by lazy {
        FragmentAlterarPerfilBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alterar_perfil, container, false)
}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()

        view.findViewById<EditText>(R.id.et_atualizar_nome)?.text = usuario?.nome!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_dtNasc)?.text = usuario?.dtNasc!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_rg)?.text = usuario?.rg!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_cpf)?.text = usuario?.cpf!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_celular)?.text = usuario?.celular!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_email)?.text = usuario?.email!!.toEditable()

    binding.btAtualizar.setOnClickListener {
        if (binding.etSenhaAntiga.text.toString().equals(usuario?.senha) && binding.etAtualizarSenha.text.toString().equals(binding.etAtualizarConfirmarSenha.text.toString())) {
            val usuarioAtualizado = Usuario(
                binding.etAtualizarNome.text.toString(),
                binding.etAtualizarEmail.text.toString(),
                usuario?.cpf!!,
                usuario?.dtNasc!!,
                binding.etAtualizarCelular.text.toString(),
                usuario?.rg!!,
                binding.etAtualizarSenha.text.toString(),
                usuario?.reservas
            )

            atualiza(usuarioAtualizado)

        } else if (binding.etSenhaAntiga.text == null && binding.etAtualizarSenha.text == null && binding.etAtualizarConfirmarSenha.text == null){
            val usuarioAtualizado = Usuario(
                binding.etAtualizarNome.text.toString(),
                binding.etAtualizarEmail.text.toString(),
                usuario?.cpf!!,
                usuario?.dtNasc!!,
                binding.etAtualizarCelular.text.toString(),
                usuario?.rg!!,
                usuario?.senha!!,
                usuario?.reservas
            )

            atualiza(usuarioAtualizado)
        } else {
            binding.etAtualizarConfirmarSenha.setError("As senhas não conferem")
        }
    }
}

fun atualiza(u : Usuario){
    val api = Retrofit.Builder()
        .baseUrl("http://44.213.7.88:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Endpoints::class.java)

    api.atualizar(u).enqueue(object : Callback<Usuario> {
        override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
            if(response.isSuccessful) {
                Toast.makeText(context, "Cadastro atualizado com sucesso", Toast.LENGTH_SHORT)
            }
        }

        override fun onFailure(call: Call<Usuario>, t: Throwable) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }

    }
    )

}
}