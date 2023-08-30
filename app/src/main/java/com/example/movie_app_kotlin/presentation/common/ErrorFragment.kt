package com.example.movie_app_kotlin.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.movie_app_kotlin.R

class ErrorFragment(
    private val isMainActivity: Boolean,
    private val onRetryButtonClickListener: OnRetryButtonClickListener
) : Fragment() {

    private lateinit var errorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_error, container, false)
        val retryButton = view.findViewById<Button>(R.id.retryButton)
        errorTextView = view.findViewById(R.id.errorMessage)

        retryButton.setOnClickListener { onRetryButtonClickListener.onButtonClick() }
        val textColor = ContextCompat.getColor(
            requireContext(),
            if (isMainActivity) R.color.primaryColor else R.color.textWhite
        )
        errorTextView.setTextColor(textColor)

        return view
    }

    fun showError(errorMessage: String) {
        if (view != null) {
            errorTextView = requireView().findViewById(R.id.errorMessage)
            errorTextView.text = errorMessage
        }
    }
}
