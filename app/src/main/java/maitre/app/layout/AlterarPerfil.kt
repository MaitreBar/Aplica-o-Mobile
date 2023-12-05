package maitre.app.layout

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import maitre.app.R
import maitre.app.data.Usuario
import maitre.app.databinding.FragmentAlterarPerfilBinding
import maitre.app.utils.Endpoints
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao
import maitre.app.utils.Sessao.usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AlterarPerfil : Fragment() {

    lateinit var binding: FragmentAlterarPerfilBinding

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // apagar o conteúdo que vem e criar essas 2 linhas para poder usar binding numa fragment
        binding = FragmentAlterarPerfilBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.btn_voltar_perfil).setOnClickListener {
            (activity as MainActivity).replaceFragment(Perfil())
        }

        view.findViewById<EditText>(R.id.et_atualizar_nome)?.text = usuario?.nome!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_dtNasc)?.text = LocalDate.parse(usuario?.dtNasc!!).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_rg)?.text = usuario?.rg!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_cpf)?.text = usuario?.cpf!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_celular)?.text = usuario?.celular!!.toEditable()
        view.findViewById<EditText>(R.id.et_atualizar_email)?.text = usuario?.email!!.toEditable()

    view.findViewById<Button>(R.id.bt_atualizar)?.setOnClickListener {
        if (binding.etSenhaAntiga.text.toString().equals(usuario?.senha) && binding.etAtualizarSenha.text.toString().equals(binding.etAtualizarConfirmarSenha.text.toString())) {
            val usuarioAtualizado = Usuario(
                usuario?.id!!,
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

        } else if (binding.etSenhaAntiga.text.isBlank() && binding.etAtualizarSenha.text.isBlank() && binding.etAtualizarConfirmarSenha.text.isBlank()){
            val usuarioAtualizado = Usuario(
                usuario?.id!!,
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

        } else if(binding.etAtualizarSenha.text.toString() != binding.etAtualizarConfirmarSenha.text.toString()) {
            binding.etAtualizarConfirmarSenha.error = "As senhas não conferem"

        } else if (binding.etSenhaAntiga.text.toString() != usuario?.senha) {
            binding.etSenhaAntiga.error = "A senha está incorreta"

        }


    }
}

    fun atualiza(u : Usuario){
        NetworkUtils.getRetrofitInstance(Sessao.urlApi)
            .atualizar(u, usuario?.id!!).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if(response.isSuccessful) {
                    usuario = u
                    Toast.makeText(context, "Cadastro atualizado com sucesso", Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}