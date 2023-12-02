package maitre.app.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maitre.app.R

/**
 * A simple [Fragment] subclass.
 * Use the [Card_Comentario.newInstance] factory method to
 * create an instance of this fragment.
 */
class Card_Comentario : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(maitre.app.ARG_PARAM1)
            param2 = it.getString(maitre.app.ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card__comentario, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Card_Comentario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Card_Comentario().apply {
                arguments = Bundle().apply {
                    putString(maitre.app.ARG_PARAM1, param1)
                    putString(maitre.app.ARG_PARAM2, param2)
                }
            }
    }
}