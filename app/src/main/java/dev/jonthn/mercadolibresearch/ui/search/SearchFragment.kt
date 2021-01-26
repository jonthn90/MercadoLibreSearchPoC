package dev.jonthn.mercadolibresearch.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dev.jonthn.mercadolibresearch.R
import dev.jonthn.mercadolibresearch.SwissTool
import dev.jonthn.mercadolibresearch.databinding.FragmentSearchBinding
import dev.jonthn.mercadolibresearch.ui.MainActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by lifecycleScope.viewModel(this)

    private lateinit var binding: FragmentSearchBinding

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@SearchFragment
        }

        binding.editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })

        binding.button.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {

        if(MainActivity.isInternet) {

            if (viewModel.isEmptySearch()) {
                binding.textInputLayout.apply {
                    error = context.getString(R.string.search_error)
                }
            } else {

                SwissTool.hideKeyboard(requireActivity())

                val action = SearchFragmentDirections.actionSearchFragmentToResultsFragment(
                    viewModel.textSearch.value!!
                )

                navController.navigate(action)
            }
        } else {

            Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }
    }
}