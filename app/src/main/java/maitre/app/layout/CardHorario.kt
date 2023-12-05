package maitre.app.layout

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import maitre.app.R
import maitre.app.databinding.FragmentCardHorarioBinding
import maitre.app.utils.SharedViewModel

class CardHorario : Fragment() {

    lateinit var binding: FragmentCardHorarioBinding
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
        binding = FragmentCardHorarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val horario = arguments?.getString("hora")!!

        binding.reservaHora.text = horario

        var selected = false
        binding.reservaHora.setOnClickListener {
            selected = !selected
            if(selected){
                binding.reservaHora.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.verdeEscuro))
                binding.reservaHora.setTextColor(ContextCompat.getColor(requireContext(), R.color.neutralLighter))
            } else {
                binding.reservaHora.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparente))
                binding.reservaHora.setTextColor(ContextCompat.getColor(requireContext(), R.color.nautralPrimaryAlt))
            }
             sharedViewModel.hora = horario
        }
    }

}