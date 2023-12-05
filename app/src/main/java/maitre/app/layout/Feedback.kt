package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import maitre.app.data.Reserva
import maitre.app.databinding.FragmentFeedbackBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao.reserva
import maitre.app.utils.Sessao.urlApi
import maitre.app.utils.Sessao.usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feedback : Fragment() {

    lateinit var binding: FragmentFeedbackBinding

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var a = 0
        var assentus = ""
        reserva!!.assentos.forEach{ assento ->
            assentus += when {
                a == 0 -> assento.id
                else -> ", ${assento.id}"
            }
            a++


        }
        binding.assentoReserva.text = assentus
        binding.nomeReserva.text = usuario!!.nome
        binding.diaReserva.text = reserva!!.dtReserva
        binding.horarioReserva.text = reserva!!.horaReserva

        binding.enviarFeedback.setOnClickListener {
            val feedback = binding.textoFeedback.text.toString()

            reserva!!.feedback = feedback

            NetworkUtils.getRetrofitInstance(urlApi)
                .atualizarReserva(reserva?.id!!, reserva!!).enqueue(object : Callback<Reserva>{
                    override fun onResponse(call: Call<Reserva>, response: Response<Reserva>) {
                        Toast.makeText(context, "Feedback realizado com sucesso", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).replaceFragment(maitre.app.layout.Reserva())
                    }

                    override fun onFailure(call: Call<Reserva>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}