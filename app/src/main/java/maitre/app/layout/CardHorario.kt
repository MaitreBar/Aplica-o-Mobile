package maitre.app.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horario = arguments?.getString("hora")!!

        binding.reservaHora.hint = horario

        binding.reservaHora.setOnClickListener {
             sharedViewModel.hora = horario
        }
    }

}