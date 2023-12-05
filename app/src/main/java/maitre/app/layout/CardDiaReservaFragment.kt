package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.R
import maitre.app.databinding.FragmentCardDiaReservaBinding

class CardDiaReservaFragment : Fragment() {
    lateinit var binding : FragmentCardDiaReservaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCardDiaReservaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val diaSemana = arguments?.getString("diaSemana")!!
        val diaMes = arguments?.getString("diaMes")!!
        binding.reservaDiaNumero.text = diaMes
        binding.reservaDiaSemana.text = diaSemana

        binding.buttonDiaReserva.setOnClickListener {
            CriacaoReserva().setDia(diaMes)
        }
    }
}