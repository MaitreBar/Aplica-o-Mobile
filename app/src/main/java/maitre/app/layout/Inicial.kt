package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import de.hdodenhof.circleimageview.CircleImageView
import maitre.app.R
import maitre.app.data.Estabelecimento
import maitre.app.databinding.FragmentInicialBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao
import maitre.app.utils.Sessao.usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Inicial : Fragment() {

    lateinit var binding: FragmentInicialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInicialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.perfilUserName.text = usuario!!.nome
        NetworkUtils.getRetrofitInstance(Sessao.urlApi)
            .getEstabelecimentos().enqueue(object : Callback<List<Estabelecimento>> {
            override fun onResponse(
                call: Call<List<Estabelecimento>>,
                response: Response<List<Estabelecimento>>
            ) {
                if (!response.isSuccessful) {
                    return
                }

                val fragmentManager = activity?.supportFragmentManager!!
                val fragmentTransaction = fragmentManager.beginTransaction()

                binding.glEstabelecimentos.removeAllViews()

                if (response.body() != null) {
                    response.body()!!.forEach { estabelecimento : Estabelecimento ->
                        val args = Bundle()

                        args.putSerializable("estabelecimento", estabelecimento)

                        fragmentTransaction.add(
                            R.id.gl_estabelecimentos,
                            CardEstabelecimentoFragment::class.java,
                            args
                        )
                    }

                    fragmentTransaction.commit()
                }
            }

            override fun onFailure(call: Call<List<Estabelecimento>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        view.findViewById<Button>(R.id.btn_ir_lista_estabelecimentos).setOnClickListener {
            (activity as MainActivity).replaceFragment(ListaEstabelecimento())
        }

        view.findViewById<CircleImageView>(R.id.btn_filtro_drinks).setOnClickListener {
            (activity as MainActivity).replaceFragment(ListaEstabelecimento())
        }

        view.findViewById<CircleImageView>(R.id.btn_filtro_petiscos).setOnClickListener {
            (activity as MainActivity).replaceFragment(ListaEstabelecimento())
        }

        view.findViewById<CircleImageView>(R.id.btn_filtro_porcao).setOnClickListener {
            (activity as MainActivity).replaceFragment(ListaEstabelecimento())
        }
        view.findViewById<CircleImageView>(R.id.btn_filtro_sobremesa).setOnClickListener {
            (activity as MainActivity).replaceFragment(ListaEstabelecimento())
        }

    }
}