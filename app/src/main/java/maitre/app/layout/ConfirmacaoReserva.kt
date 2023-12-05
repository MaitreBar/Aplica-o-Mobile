package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import maitre.app.R
import maitre.app.data.Assento
import maitre.app.data.AssentoReserva
import maitre.app.data.Reserva
import maitre.app.databinding.FragmentConfirmacaoReservaBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao.estabelecimento
import maitre.app.utils.Sessao.urlApi
import maitre.app.utils.Sessao.usuario
import maitre.app.utils.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmacaoReserva : Fragment() {
    lateinit var binding : FragmentConfirmacaoReservaBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentConfirmacaoReservaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val novaReserva = sharedViewModel.novaReserva

        binding.btnReservaConfirmar.setOnClickListener {
            NetworkUtils.getRetrofitInstance(urlApi)
                .criarReserva(novaReserva).enqueue(object : Callback<Reserva> {
                    override fun onResponse(call: Call<Reserva>, response: Response<Reserva>) {
                        if(response.isSuccessful){
                        novaReserva.assentos.forEach { assento ->
                            NetworkUtils.getRetrofitInstance(urlApi)
                                .atualizarAssento(assento.id, Assento(assento.id, assento.disponivel, response.body()!!, estabelecimento!! )).enqueue(object : Callback<Assento> {
                                    override fun onResponse(
                                        call: Call<Assento>,
                                        response: Response<Assento>
                                    ) {
                                        Toast.makeText(context, "Reserva criada com sucesso", Toast.LENGTH_SHORT).show()

                                        (activity as MainActivity).replaceFragment(Reserva())
                                    }

                                    override fun onFailure(call: Call<Assento>, t: Throwable) {
                                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                                    }

                                })
                            }
                        }
                    }

                    override fun onFailure(call: Call<Reserva>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}