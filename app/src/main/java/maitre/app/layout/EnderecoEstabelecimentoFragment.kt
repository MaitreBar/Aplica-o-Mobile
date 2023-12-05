package maitre.app.layout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.R
import maitre.app.databinding.FragmentEnderecoEstabelecimentoBinding
import maitre.app.utils.Sessao

class EnderecoEstabelecimentoFragment : Fragment() {
    lateinit var binding : FragmentEnderecoEstabelecimentoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEnderecoEstabelecimentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.estabelecimentoEndereco.text = "${Sessao.estabelecimento?.logradouro.toString()}, ${Sessao.estabelecimento?.numero}"
    }

}