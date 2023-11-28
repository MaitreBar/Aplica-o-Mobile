package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import maitre.app.R
import maitre.app.data.Estabelecimento
import maitre.app.databinding.FragmentInicialBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao
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
                    response.body()!!.forEach {
                        val args = Bundle()
                        args.putString("nome", it.nome)
                        args.putString("descricao", it.descricao)

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


    }
}