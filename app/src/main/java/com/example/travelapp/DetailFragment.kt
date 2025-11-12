package com.example.travelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelapp.databinding.FragmentDetailBinding

const val ROW_POSITION = "ROW_POSITION"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class DetailFragment : Fragment() {
    private var _binding:FragmentDetailBinding? = null
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
        _binding = FragmentDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sights = getSights(resources, jsonFileName)
        binding.detailKind.text = sights[position].kind
        binding.detailName.text = sights[position].name
        binding.detailDescription.text = sights[position].description
        var img = resources.getIdentifier(sights[position].imageName, "drawable", context?.packageName)
        binding.detailImage.setImageResource(img)
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