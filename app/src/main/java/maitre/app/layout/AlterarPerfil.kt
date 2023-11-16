package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.set
import maitre.app.R
import maitre.app.databinding.FragmentAlterarPerfilBinding
import maitre.app.utils.Sessao.usuario

class AlterarPerfil : Fragment() {

    val binding by lazy {
        FragmentAlterarPerfilBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alterar_perfil, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()

        binding.etAtualizarNome.setText(usuario.nome)
//        view?.findViewById<EditText>(R.id.et_atualizar_dtNasc)?.setText(usuario.dtNasc)
//        view?.findViewById<EditText>(R.id.et_atualizar_rg)?.setText(usuario.rg)
//        view?.findViewById<EditText>(R.id.et_atualizar_cpf)?.setText(usuario.cpf)
//        view?.findViewById<EditText>(R.id.et_atualizar_celular)?.setText(usuario.celular)
//        view?.findViewById<EditText>(R.id.et_atualizar_email)?.setText(usuario.email)

    }
}