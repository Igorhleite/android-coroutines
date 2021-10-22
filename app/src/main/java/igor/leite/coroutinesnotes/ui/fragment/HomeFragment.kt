package igor.leite.coroutinesnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import igor.leite.coroutinesnotes.databinding.FragmentHomeBinding
import igor.leite.coroutinesnotes.ui.viewmodel.HomeViewModel
import igor.leite.coroutinesnotes.utils.RequestState

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActions()
        initObservers()
    }

    private fun initObservers() {
        viewModel.randomCardState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.ResponseSuccess -> {
                    with(binding) {
                        tvCardName.text = it.value.name
                        tvCardType.text = it.value.type
                    }
                }
                is RequestState.ResponseFailure -> {
                    Toast.makeText(requireActivity(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
                is RequestState.ResponseException -> {
                    Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is RequestState.Loading -> {
                    it.status.let { status ->
                        binding.loading.isVisible = status
                    }
                }
            }

        })
    }

    private fun setActions() {
        binding.btCallApi.setOnClickListener {
            viewModel.getRandomCard()
        }
    }

}