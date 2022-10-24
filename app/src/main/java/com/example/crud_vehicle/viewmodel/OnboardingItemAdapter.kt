package com.example.crud_vehicle.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.crud_vehicle.R
import com.example.crud_vehicle.model.OnboardingItem

class OnboardingItemAdapter (private val onboardingItems: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>(){

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageOnboarding = view.findViewById<LottieAnimationView>(R.id.imageItem)
        private val textTile = view.findViewById<TextView>(R.id.titleItem)
        private val descriptionTitle = view.findViewById<TextView>(R.id.descriptionItem)

        fun bind(onboardingItem: OnboardingItem) {
            imageOnboarding.setAnimation(onboardingItem.onboardingImage)
            textTile.text = onboardingItem.title
            descriptionTitle.text = onboardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }
}