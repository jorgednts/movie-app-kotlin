package com.example.movie_app_kotlin.presentation.movie_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app_kotlin.R
import com.example.movie_app_kotlin.databinding.ActivityMovieListBinding
import com.example.movie_app_kotlin.di.ApplicationComponent
import com.example.movie_app_kotlin.di.ApplicationModule
import com.example.movie_app_kotlin.di.DaggerApplicationComponent
import com.example.movie_app_kotlin.presentation.common.ErrorFragment
import com.example.movie_app_kotlin.presentation.common.OnRetryButtonClickListener
import com.example.movie_app_kotlin.presentation.movie_details.MovieDetailsActivity
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    private val component: ApplicationComponent? by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: MovieListViewModel
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var adapter: MovieCardAdapter
    private lateinit var errorFragment: ErrorFragment
    private var onButtonClickListener = object : OnButtonClickListener {
        override fun onButtonClick(movieId: Int) {
            val intent = Intent(applicationContext, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId)
            startActivity(intent)
        }
    }
    private var onRetryButtonClickListener = object : OnRetryButtonClickListener {
        override fun onButtonClick() {
            viewModel.getMovieList()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component?.injectInMovieListActivity(this)
        setupView()
        setupObservers()
        viewModel.getMovieList()
    }

    private fun setupView() {
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupErrorFragment()
    }

    private fun setupObservers() {
        setupMovieListObserver()
        setupErrorMessageObserver()
        setupLoadingObserver()
    }

    private fun setupMovieListObserver() {
        viewModel.movieList.observe(
            this
        ) { movieListData ->
            adapter = MovieCardAdapter(emptyList(), onButtonClickListener)
            configureRecyclerView()
            adapter.updateData(movieListData)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupErrorMessageObserver() {
        viewModel.errorMessage.observe(this) { message ->
            if (message.equals("")) {
                binding.errorView.visibility = View.GONE
            } else {
                binding.errorView.visibility = View.VISIBLE
                errorFragment.showError(message)
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupLoadingObserver() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.errorView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun configureRecyclerView() {
        binding.movieListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.movieListRecyclerView.adapter = adapter
        binding.movieListRecyclerView.setHasFixedSize(true)
    }

    private fun setupErrorFragment() {
        errorFragment = ErrorFragment(true, onRetryButtonClickListener)
        supportFragmentManager.beginTransaction()
            .add(R.id.errorView, errorFragment)
            .commit()
    }
}