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

            popup.showAsDropDown(binding.reservaLayout)
        }

    }
}
