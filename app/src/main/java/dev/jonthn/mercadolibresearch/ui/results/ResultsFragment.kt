package dev.jonthn.mercadolibresearch.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dev.jonthn.mercadolibresearch.databinding.FragmentResultsBinding
import dev.jonthn.mercadolibresearch.ui.common.EventObserver
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class ResultsFragment : Fragment() {

    private var binding: FragmentResultsBinding? = null
    private val args: ResultsFragmentArgs by navArgs()

    private lateinit var navController: NavController

    private lateinit var adapter: ResultsAdapter

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

        navController = view.findNavController()

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@ResultsFragment
        }

        adapter = ResultsAdapter(viewModel::onItemClicked)
        binding?.recycler?.adapter = adapter

        viewModel.navigateToItem.observe(viewLifecycleOwner, EventObserver { id ->
            val action = ResultsFragmentDirections.actionResultsFragmentToDetailFragment(id)
            navController.navigate(action)
        })

    }
}