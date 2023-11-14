package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import maitre.app.R
import maitre.app.utils.Sessao.usuario

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Perfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class Perfil : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)

        Toast.makeText(context, "$usuario", Toast.LENGTH_LONG)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()

        view.findViewById<Button>(R.id.btn_perfil_reservas).setOnClickListener {
            (activity as MainActivity).binding.bottomNavigationView.selectedItemId =
                R.id.reserva_nav

            Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()
        }

        view.findViewById<Button>(R.id.btn_perfil_reservar).setOnClickListener {
            (activity as MainActivity).replaceFragment(CriacaoReserva())

            Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()
        }

        view.findViewById<Button>(R.id.btn_perfil_alterar).setOnClickListener {
            (activity as MainActivity).replaceFragment(AlterarPerfil())

            Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()

            view.findViewById<EditText>(R.id.et_atualizar_nome).setText(usuario.nome)
            view.findViewById<EditText>(R.id.et_atualizar_dtNasc).setText(usuario.dtNasc)
            view.findViewById<EditText>(R.id.et_atualizar_rg).setText(usuario.rg)
            view.findViewById<EditText>(R.id.et_atualizar_cpf).setText(usuario.cpf)
            view.findViewById<EditText>(R.id.et_atualizar_celular).setText(usuario.celular)
            view.findViewById<EditText>(R.id.et_atualizar_email).setText(usuario.email)

        }

//        view.findViewById<Button>(R.id.btn_perfil_chat).setOnClickListener {
//            (activity as MainActivity).replaceFragment(Chat())
//        }

        view.findViewById<Button>(R.id.btn_perfil_logout).setOnClickListener {

            Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()
        }
    }
}