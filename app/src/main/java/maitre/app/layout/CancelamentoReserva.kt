package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import maitre.app.R
import maitre.app.data.Reserva
import maitre.app.databinding.FragmentCancelamentoReservaBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CancelamentoReserva : Fragment() {
    lateinit var binding: FragmentCancelamentoReservaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCancelamentoReservaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReservaCancelar.setOnClickListener {
            NetworkUtils.getRetrofitInstance(Sessao.urlApi)
                .deleteReserva(arguments?.getString("id")!!)
                .enqueue(object : Callback<Reserva> {
                    override fun onResponse(call: Call<Reserva>, response: Response<Reserva>) {
                        if (!response.isSuccessful) {
                            return
                        }
                        Toast.makeText(context, "Reserva excluída com sucesso", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<Reserva>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }

                }
                )
        }

        binding.btnReservaFeedback.setOnClickListener {
            (activity as MainActivity).replaceFragment(Feedback())
        }
    }
}