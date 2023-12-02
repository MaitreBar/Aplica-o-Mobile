package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import maitre.app.R
import maitre.app.data.Estabelecimento
import maitre.app.databinding.FragmentReservaBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao
import maitre.app.utils.Sessao.usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Reserva : Fragment() {

    lateinit var binding: FragmentReservaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentReservaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NetworkUtils.getRetrofitInstance(Sessao.urlApi)
            .getReservas(usuario!!.id).enqueue(object : Callback<List<Estabelecimento>> {
                override fun onResponse(
                    call: Call<List<Estabelecimento>>,
                    response: Response<List<Estabelecimento>>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }

                    val fragmentManager = activity?.supportFragmentManager!!
                    val fragmentTransaction = fragmentManager.beginTransaction()

                    if (response.body() != null){
                        var i = 0
                        binding.scrollReservas.removeAllViews()
                        response.body()!!.forEach { estabelecimento ->
                            estabelecimento.reservas.forEach{ reserva ->
                                    usuario!!.reservas!!.forEach { reserva2 ->
                                    if(!reserva.checkout && reserva2.id == reserva.id){
                                        val args = Bundle()
                                        var a = 0
                                        var assentus = ""
                                        reserva2.assentos.forEach{ assento ->
                                            assentus += when {
                                                a == 0 -> assento.id
                                                else -> ", ${assento.id}"
                                            }
                                            a++
                                        }
                                        args.putString("data", LocalDate.parse(reserva2.dtReserva).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                                        args.putString("hora", reserva2.horaReserva)
                                        args.putString("assento", assentus)
                                        args.putString("nome_usuario", usuario!!.nome)
                                        args.putString("nome_estabelecimento", estabelecimento.nome)
                                        args.putString("id", reserva2.id)

                                        fragmentTransaction.add(
                                            R.id.scroll_reservas,
                                            CardReservaFragment::class.java,
                                            args
                                        )
                                    }
                                }
                            }
                            i++
                        }
                        fragmentTransaction.commit()
                    }
                }

                override fun onFailure(call: Call<List<Estabelecimento>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })

    }



}