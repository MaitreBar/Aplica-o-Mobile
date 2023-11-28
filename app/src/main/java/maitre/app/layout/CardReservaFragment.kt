package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.databinding.FragmentCardReservaBinding


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
    }
}
