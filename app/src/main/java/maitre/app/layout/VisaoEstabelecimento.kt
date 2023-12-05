package maitre.app.layout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import maitre.app.R
import maitre.app.data.Usuario
import maitre.app.databinding.FragmentVisaoEstabelecimentoBinding
import maitre.app.utils.NetworkUtils
import maitre.app.utils.Sessao.estabelecimento
import maitre.app.utils.Sessao.urlApi
import maitre.app.utils.Sessao.usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        binding.tvVisaoNome.text = e.nome
        binding.tvVisaoTag.text = removeCharacters(e.tags, "[]\"")
        binding.btnVisaoVoltar.setOnClickListener {
            estabelecimento = null
            (activity as MainActivity).replaceFragment(Inicial())
        }

        binding.btnVisaoReservar.setOnClickListener {
            estabelecimento = e
            (activity as MainActivity).replaceFragment(CriacaoReserva())
        }

        binding.btnEntrarFilaVisaoEstabelecimento.setOnClickListener {
            val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_fila, null)
            val popup = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )

            if (usuario?.id!! == "4") {
                popupView.findViewById<TextView>(R.id.posicao_fila).text = "0"
            } else {
                popupView.findViewById<TextView>(R.id.posicao_fila).text = "1"
            }

//            NetworkUtils.getRetrofitInstance(urlApi)
//                .entrarNaFila(usuario!!.id).enqueue(object : Callback<String>{
//                    override fun onResponse(call: Call<String>, response: Response<String>) {
//                        posicaoFila(popupView)
//                    }
//
//                    override fun onFailure(call: Call<String>, t: Throwable) {
////                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                        Log.e("error", t.message!!)
//                    }
//
//                })

            popupView.findViewById<ImageButton>(R.id.fechar_fila).setOnClickListener {
                popup.dismiss()
            }

            popup.showAsDropDown(binding.btnEntrarFilaVisaoEstabelecimento)

        }

        binding.btnEnderecoVisaoEstabelecimento.setOnClickListener {
            val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_endereco_estabelecimento, null)
            val popup = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            popupView.findViewById<ImageButton>(R.id.btn_fechar_endereco).setOnClickListener {
                popup.dismiss()
            }
            popupView.findViewById<TextView>(R.id.estabelecimento_endereco).text = "${estabelecimento?.logradouro.toString()}, ${estabelecimento?.numero}"
            popup.showAsDropDown(binding.btnEnderecoVisaoEstabelecimento)
        }

        binding.btnTelefoneVisaoEstabelecimento.setOnClickListener {
            val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_telefone_estabelecimento, null)
            val popup = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            popupView.findViewById<ImageButton>(R.id.btn_fechar_telefone).setOnClickListener {
                popup.dismiss()
            }

            popupView.findViewById<TextView>(R.id.estabelecimento_telefone).text = estabelecimento?.telefoneContato
            popup.showAsDropDown(binding.btnTelefoneVisaoEstabelecimento)
        }

        binding.glComentario.removeAllViews()
        NetworkUtils.getRetrofitInstance(urlApi)
            .getReservasEstabelecimentos(e.idEstabelecimento).enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (!response.isSuccessful) {
                    return
                }

                val fragmentManager = activity?.supportFragmentManager!!
                val fragmentTransaction = fragmentManager.beginTransaction()

                if (response.body() != null){
                    binding.glComentario.removeAllViews()
                    response.body()!!.forEach { usuario ->
                        usuario.reservas!!.forEach{ reserva ->
                            e.reservas.forEach { reserva2 ->
                                if(reserva2.id == reserva.id){
                                    if (reserva2.checkOut && !reserva2.feedback.isNullOrBlank()){
                                        val args = Bundle()

                                        args.putString("feedback", reserva2.feedback)
                                        args.putString("nome", usuario.nome)

                                        fragmentTransaction.add(
                                            R.id.gl_comentario,
                                            CardComentario::class.java,
                                            args
                                        )
                                    }
                                }
                            }
                        }
                    }
                    fragmentTransaction.commit()
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    fun posicaoFila(popupView: View) {
//        NetworkUtils.getRetrofitInstance(urlApi)
//            .posicaoFila(usuario!!.id).enqueue(object : Callback<Int>{
//                override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                    val pos =  response.body() ?: 0
//                    for(posicao in pos downTo 0 )
//                    popupView.findViewById<TextView>(R.id.posicao_fila).text = "$pos"
//                }
//
//                override fun onFailure(call: Call<Int>, t: Throwable) {
//                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
//                    Log.e("error", t.message!!)
//                }
//
//            })
    }

}

fun removeCharacters(input: String, charactersToRemove: String): String {
    return input.replace(Regex("[" + Regex.escape(charactersToRemove) + "]"), "")
}


