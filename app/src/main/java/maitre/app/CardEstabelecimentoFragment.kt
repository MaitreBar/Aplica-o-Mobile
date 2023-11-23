package maitre.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.databinding.FragmentCardEstabelecimentoBinding
import maitre.app.databinding.FragmentInicialBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CardEstabelecimentoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardEstabelecimentoFragment : Fragment() {

    lateinit var binding: FragmentCardEstabelecimentoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCardEstabelecimentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEstabelecimentoNome.text = arguments?.getString("nome")
    }

}