package com.example.travelapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.data.CategoryRepository
import com.example.travelapp.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_JSON_FILE_NAME = "JSON_FILE_NAME"

        fun newInstance(jsonFileName: String): ListFragment {
            val fragment = ListFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_JSON_FILE_NAME, jsonFileName)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonFileName = arguments?.getString(ARG_JSON_FILE_NAME) ?: "kankou1jp.json"

        // Use CategoryRepository for centralized, cached data loading
        val repository = CategoryRepository(requireContext())
        val sights = repository.getSightsByFileName(jsonFileName)

        binding.root.apply {
            layoutManager = when {
                resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ->
                    LinearLayoutManager(context)
                else -> GridLayoutManager(context, 2)
            }
            adapter = SightAdapter(context, sights).apply {
                setOnItemClickListener { position: Int ->
                    fragmentManager?.let { manager: FragmentManager ->
                        val tag = "DetailFragment"
                        var fragment = manager.findFragmentByTag(tag)
                        if (fragment == null) {
                            fragment = DetailFragment.newInstance(jsonFileName, position)
                            manager.beginTransaction().apply {
                                replace(R.id.content, fragment, tag)
                                addToBackStack(null)
                            }.commit()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
