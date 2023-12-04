package maitre.app.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maitre.app.R
import maitre.app.databinding.FragmentCardHorarioBinding

class CardHorario : Fragment() {

    lateinit var binding: FragmentCardHorarioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCardHorarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reservaHora.hint = arguments?.getString("hora")

        binding.reservaHora.setOnClickListener {
            CriacaoReserva().setHorario(arguments?.getString("hora")!!)
        }
    }

}