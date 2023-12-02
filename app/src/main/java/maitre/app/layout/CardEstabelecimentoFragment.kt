package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.data.Estabelecimento
import maitre.app.databinding.FragmentCardEstabelecimentoBinding
import maitre.app.utils.Sessao


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
        val estabelecimento : Estabelecimento = (arguments?.getSerializable("estabelecimento") as Estabelecimento?)!!
        binding.tvEstabelecimentoNome.text = estabelecimento.nome

        binding.btnVisaoEstabelecimento.setOnClickListener {
            Sessao.estabelecimento = estabelecimento
            (activity as MainActivity).replaceFragment(VisaoEstabelecimento())
        }
    }

}