package dev.jonthn.mercadolibresearch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dev.jonthn.mercadolibresearch.databinding.FragmentDetailBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by lifecycleScope.viewModel(this) {
        parametersOf(args.idItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailFragment
        }

        Toast.makeText(requireContext(), args.idItem, Toast.LENGTH_LONG).show()
    }

}