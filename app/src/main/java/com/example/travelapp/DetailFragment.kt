package com.example.travelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelapp.data.CategoryRepository
import com.example.travelapp.databinding.FragmentDetailBinding

const val ROW_POSITION = "ROW_POSITION"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var position: Int = 0

    private var jsonFileName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ROW_POSITION)
            jsonFileName = it.getString("JSON_FILE_NAME", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use CategoryRepository for centralized, cached data loading
        val repository = CategoryRepository(requireContext())
        val sights = repository.getSightsByFileName(jsonFileName)

        if (position < 0 || position >= sights.size) return

        val sight = sights[position]
        binding.detailKind.text = sight.kind
        binding.detailName.text = sight.name
        binding.detailDescription.text = sight.description

        // Load image with fallback to placeholder for missing drawables
        val imgResId = requireContext().resources.getIdentifier(
            sight.imageName, "drawable", requireContext().packageName
        )
        val resolvedResId = if (imgResId != 0) imgResId else R.drawable.ic_placeholder
        binding.detailImage.setImageResource(resolvedResId)

        // Accessibility
        binding.detailImage.contentDescription = sight.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(jsonFileName: String, position: Int): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString("JSON_FILE_NAME", jsonFileName)
            args.putInt(ROW_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
}