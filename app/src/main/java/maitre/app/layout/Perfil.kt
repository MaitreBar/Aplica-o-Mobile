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
import maitre.app.databinding.FragmentPerfilBinding
import maitre.app.utils.Sessao.usuario

class Perfil : Fragment() {

    val binding by lazy {
        FragmentPerfilBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()

        binding.tvPerfilUserName.text = usuario.nome

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

        }

//        view.findViewById<Button>(R.id.btn_perfil_chat).setOnClickListener {
//            (activity as MainActivity).replaceFragment(Chat())
//        }

        view.findViewById<Button>(R.id.btn_perfil_logout).setOnClickListener {

            Toast.makeText(context, "$usuario", Toast.LENGTH_LONG).show()
        }
    }
}