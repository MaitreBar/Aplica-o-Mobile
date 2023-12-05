package maitre.app.layout

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import maitre.app.R
import maitre.app.utils.Sessao.usuario

class Perfil : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        view.findViewById<TextView>(R.id.tv_perfil_user_name)?.text = usuario?.nome
        view.findViewById<TextView>(R.id.tv_perfil_user_celular)?.text = usuario?.celular
        view.findViewById<TextView>(R.id.tv_perfil_user_email)?.text = usuario?.email


        view.findViewById<Button>(R.id.btn_perfil_reservas).setOnClickListener {
            (activity as MainActivity).binding.bottomNavigationView.selectedItemId =
                R.id.reserva_nav
        }

        view.findViewById<Button>(R.id.btn_perfil_reservar).setOnClickListener {
            (activity as MainActivity).binding.bottomNavigationView.selectedItemId =
                R.id.home_nav
        }

        view.findViewById<Button>(R.id.btn_perfil_alterar).setOnClickListener {
            (activity as MainActivity).replaceFragment(AlterarPerfil())

        }

        view.findViewById<Button>(R.id.btn_perfil_chat).setOnClickListener {
            (activity as MainActivity).replaceFragment(ChatbotFragment())
            (activity as MainActivity).hideBottomNavigationView()

        }

        view.findViewById<Button>(R.id.btn_perfil_logout).setOnClickListener {

            usuario = null
            val intent = Intent(context, Login::class.java)

            startActivity(intent)
        }
    }
}