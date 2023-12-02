package maitre.app.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maitre.app.R
import maitre.app.databinding.FragmentCardComentarioBinding

class CardComentario : Fragment() {

    lateinit var binding : FragmentCardComentarioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCardComentarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.visaoComentario.text = arguments?.getString("feedback")
        binding.visaoNomeComentario.text = arguments?.getString("nome")
    }

}