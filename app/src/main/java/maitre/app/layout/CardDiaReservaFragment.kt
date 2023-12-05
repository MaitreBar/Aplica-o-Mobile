package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import maitre.app.R
import maitre.app.databinding.FragmentCardDiaReservaBinding
import maitre.app.utils.SharedViewModel

class CardDiaReservaFragment : Fragment() {
    lateinit var binding : FragmentCardDiaReservaBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }
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

        var selected = false
        binding.buttonDiaReserva.setOnClickListener {
            selected = !selected
            if(selected){
                binding.buttonDiaReserva.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.verdeEscuro))
            } else {
                binding.buttonDiaReserva.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparente))
            }
            sharedViewModel.dia = diaMes
        }
    }
}

