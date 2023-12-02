package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.databinding.FragmentVisaoEstabelecimentoBinding
import maitre.app.utils.Sessao.estabelecimento

class VisaoEstabelecimento : Fragment() {
    lateinit var binding: FragmentVisaoEstabelecimentoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentVisaoEstabelecimentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val e = estabelecimento!!

        binding.tvVisaoDescricao.text = e.descricao
        binding.tvVisaoFaixa.text = e.faixaDePreco
        binding.tvVisaoNome.text = e.nome
        binding.tvVisaoTag.text = e.tags

        binding.btnVisaoVoltar.setOnClickListener {
            estabelecimento = null
            (activity as MainActivity).replaceFragment(Inicial())
        }
    }

}