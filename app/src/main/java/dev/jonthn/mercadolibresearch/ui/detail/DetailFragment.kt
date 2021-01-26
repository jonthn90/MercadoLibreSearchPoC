package dev.jonthn.mercadolibresearch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.slidertypes.DefaultSliderView
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

        viewModel.item.observe(viewLifecycleOwner, Observer {
            setURLS()
        })
    }

    private fun setURLS() {

        viewModel.item.value?.body?.pictures?.forEach { picture ->
            binding?.slider?.addSlider(DefaultSliderView(requireContext()).apply {
                image(picture.secure_url)
                setRequestOption(RequestOptions().fitCenter())
                setProgressBarVisible(true)
            })
        }

        binding?.slider?.apply {
            setCustomIndicator(binding?.customIndicator)
            setDuration(4000)
            stopCyclingWhenTouch(true)
            currentPosition = if (viewModel.item.value?.body?.pictures?.size == 1) 0 else 1

        }
    }
}