package com.example.travelapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.databinding.CardLayoutBinding

/**
 * RecyclerView adapter for displaying tourism sights.
 * Uses ViewBinding for type-safe view access.
 * Gracefully handles missing images with a placeholder drawable.
 */
class SightAdapter(
    private val context: Context,
    private val sights: List<Sight>
) : RecyclerView.Adapter<SightAdapter.ViewHolder>() {

    private var listener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    /** ViewHolder using ViewBinding for type-safe view references. */
    class ViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sight = sights[position]
        with(holder.binding) {
            name.text = sight.name
            description.text = sight.description
            kind.text = sight.kind

            // Load image with fallback to placeholder for missing drawables
            val imageResId = context.resources.getIdentifier(
                sight.imageName, "drawable", context.packageName
            )
            val resolvedResId = if (imageResId != 0) imageResId else R.drawable.ic_placeholder
            image.setImageResource(resolvedResId)

            // Accessibility: describe the card content for screen readers
            root.contentDescription = context.getString(
                R.string.sight_content_description,
                sight.name,
                sight.kind
            )
        }

        holder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = sights.size
}