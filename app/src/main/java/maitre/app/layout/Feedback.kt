package maitre.app.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maitre.app.R
import maitre.app.databinding.FragmentFeedbackBinding

class Feedback : Fragment() {

    lateinit var binding: FragmentFeedbackBinding
            override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
                binding = FragmentFeedbackBinding.inflate(inflater, container, false)
                return binding.root
        }

}