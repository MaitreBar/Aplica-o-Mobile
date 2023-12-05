package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.R
import maitre.app.databinding.FragmentTelefoneEstabelecimentoBinding
import maitre.app.utils.Sessao.estabelecimento

class TelefoneEstabelecimento : Fragment() {
    lateinit var binding: FragmentTelefoneEstabelecimentoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTelefoneEstabelecimentoBinding.inflate(inflater, container, false)
        return binding.root
    }


}