package com.frxhaikal_plg.ingrevia.ui.detailrecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frxhaikal_plg.ingrevia.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class NutritionFactsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_nutrition_facts, container, false)

        val chartSodium: CircularProgressIndicator = rootView.findViewById(R.id.chartSodium)
        val chartFats: CircularProgressIndicator = rootView.findViewById(R.id.chartFats)
        val chartProtein: CircularProgressIndicator = rootView.findViewById(R.id.chartProtein)

        chartSodium.setProgressCompat(50, true)  // 50% sodium
        chartFats.setProgressCompat(30, true)    // 30% fats
        chartProtein.setProgressCompat(70, true) // 70% protein

        return rootView
    }
}
