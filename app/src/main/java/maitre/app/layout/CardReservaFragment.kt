package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.Toast
import maitre.app.R
import maitre.app.databinding.FragmentCardReservaBinding
import maitre.app.utils.NetworkUtils
import maitre.app.data.Reserva
import maitre.app.utils.Sessao
import maitre.app.utils.Sessao.reserva
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CardReservaFragment : Fragment() {
    lateinit var binding: FragmentCardReservaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCardReservaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.assentoReserva.text = arguments?.getString("assento")
        binding.diaReserva.text = arguments?.getString("data")
        binding.horarioReserva.text = arguments?.getString("hora")
        binding.nomeReserva.text = arguments?.getString("nome_usuario")
        binding.reservaNomeEstabelecimento.text = arguments?.getString("nome_estabelecimento")
        binding.reservaLayout.setOnClickListener {
            val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_cancelamento_reserva, null)
            val popup = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            popupView.findViewById<ImageButton>(R.id.btn_reserva_fechar).setOnClickListener {
            popup.dismiss()
            }
            reserva = arguments?.getSerializable("reserva") as Reserva

            if(reserva!!.checkOut && reserva!!.checkIn) {
                popupView.findViewById<Button>(R.id.btn_reserva_feedback).visibility = View.VISIBLE
                popupView.findViewById<Button>(R.id.btn_reserva_cancelar).visibility = View.GONE
            } else {
                popupView.findViewById<Button>(R.id.btn_reserva_feedback).visibility = View.GONE
                popupView.findViewById<Button>(R.id.btn_reserva_cancelar).visibility = View.VISIBLE
            }

            popupView.findViewById<Button>(R.id.btn_reserva_cancelar).setOnClickListener {
                NetworkUtils.getRetrofitInstance(Sessao.urlApi)
                    .deleteReserva(arguments?.getString("id")!!)
                    .enqueue(object : Callback<Reserva> {
                        override fun onResponse(call: Call<Reserva>, response: Response<Reserva>) {
                            if (!response.isSuccessful) {
                                return
                            }
                            Toast.makeText(context, "Reserva excluída com sucesso", Toast.LENGTH_SHORT).show()
                            popup.dismiss()
                            (activity as MainActivity).replaceFragment(Reserva())
                        }

                        override fun onFailure(call: Call<Reserva>, t: Throwable) {
                            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                    )
            }

            popupView.findViewById<Button>(R.id.btn_reserva_feedback).setOnClickListener {
                popup.dismiss()

                reserva = arguments?.getSerializable("reserva") as Reserva

                (activity as MainActivity).replaceFragment(Feedback())
            }

            popup.showAsDropDown(binding.reservaLayout)
        }

    }
}
