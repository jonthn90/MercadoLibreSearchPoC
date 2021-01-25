package dev.jonthn.mercadolibresearch.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import dev.jonthn.mercadolibresearch.R
import dev.jonthn.mercadolibresearch.databinding.FragmentResultsBinding
import dev.jonthn.mercadolibresearch.databinding.FragmentSearchBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class ResultsFragment : Fragment() {

    private var binding: FragmentResultsBinding? = null
    private val args: ResultsFragmentArgs by navArgs()

    private val viewModel: ResultsViewModel by lifecycleScope.viewModel(this) {
        parametersOf(args.textSearch)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@ResultsFragment
        }

        Toast.makeText(requireContext(), args.textSearch, Toast.LENGTH_LONG).show()
    }

}