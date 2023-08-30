package com.example.movie_app_kotlin.presentation.movie_details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movie_app_kotlin.R
import com.example.movie_app_kotlin.databinding.ActivityMovieDetailsBinding
import com.example.movie_app_kotlin.di.ApplicationComponent
import com.example.movie_app_kotlin.di.ApplicationModule
import com.example.movie_app_kotlin.di.DaggerApplicationComponent
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.presentation.common.ErrorFragment
import com.example.movie_app_kotlin.presentation.common.HorizontalListAdapter
import com.example.movie_app_kotlin.presentation.common.OnRetryButtonClickListener
import com.example.movie_app_kotlin.presentation.common.formatInt
import java.util.Locale
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }

    private val component: ApplicationComponent? by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var errorFragment: ErrorFragment
    private lateinit var onRetryButtonClickListener: OnRetryButtonClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component?.injectInMovieDetailsActivity(this)
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        onRetryButtonClickListener = object : OnRetryButtonClickListener {
            override fun onButtonClick() {
                viewModel.getMovieDetails(movieId)
            }
        }

        setupViews()
        setupObservers()
        viewModel.getMovieDetails(movieId)
    }

    private fun setupViews() {
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupErrorFragment()
        binding.errorView.visibility = View.GONE
    }

    private fun setupObservers() {
        setupMovieDetailsObserver()
        setupErrorMessageObserver()
        setupLoadingObserver()
    }

    private fun setupLoadingObserver() {
        viewModel.isLoading.observe(this) {
            if (it) {
                title = "Loading..."
                binding.errorView.visibility = View.GONE
                binding.successLayout.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setupErrorMessageObserver() {
        viewModel.errorMessage.observe(this) {
            if (it.equals("")) {
                binding.errorView.visibility = View.GONE
            } else {
                errorFragment.showError(it)
                title = ""
                binding.successLayout.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupMovieDetailsObserver() {
        viewModel.movieDetails.observe(this, ::configureView)
    }

    private fun configureView(movieDetails: MovieDetailsModel) {
        binding.progressBar.visibility = View.GONE
        binding.successLayout.visibility = View.VISIBLE
        title = movieDetails.title

        binding.originalTitle.text = movieDetails.originalTitle
        val language = "(" + movieDetails.originalLanguage.uppercase(Locale.getDefault()) + ")"
        binding.originalLanguage.text = language

        binding.releaseDate.text = movieDetails.releaseDate
        binding.duration.text = getString(R.string.movie_duration_format, movieDetails.runtime)

        Glide.with(this)
            .load(movieDetails.posterUrl)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.poster)
        binding.genreList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HorizontalListAdapter(movieDetails.genres)
        binding.genreList.adapter = adapter
        binding.overview.text = movieDetails.overview

        binding.rating.text =
            getString(R.string.movie_vote_average_format, movieDetails.voteAverage)
        binding.status.text = movieDetails.status

        val productionCompaniesNames: List<String> =
            movieDetails.productionCompanies.map {
                it.name
            }
        binding.productionCompanies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val productionCompaniesAdapter = HorizontalListAdapter(productionCompaniesNames)
        binding.productionCompanies.adapter = productionCompaniesAdapter
        binding.revenue.text =
            getString(R.string.movie_budget_format, movieDetails.budget.formatInt())
        binding.availableIn.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val availableInAdapter = HorizontalListBorderlessAdapter(movieDetails.spokenLanguages)
        binding.availableIn.adapter = availableInAdapter
    }

    private fun setupErrorFragment() {
        errorFragment = ErrorFragment(false, onRetryButtonClickListener)
        supportFragmentManager.beginTransaction()
            .add(R.id.errorView, errorFragment)
            .commit()
    }
}