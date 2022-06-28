package com.ingrid.simuladordepartidas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ingrid.simuladordepartidas.R
import com.ingrid.simuladordepartidas.data.MatchesAPI
import com.ingrid.simuladordepartidas.databinding.ActivityMainBinding
import com.ingrid.simuladordepartidas.domain.Match
import com.ingrid.simuladordepartidas.ui.adapter.MatchesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var matchesAPI: MatchesAPI
    lateinit var binding: ActivityMainBinding
    lateinit var matchesAdapter: MatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHTTPClient()
        setupMatchList()
        setupMatchRefresh()
        setupFloatActionButton()
    }

    private fun setupHTTPClient() {
        val BASE_URL = "https://luizsfl.github.io/matches-simulator-api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchesAPI = retrofit.create(MatchesAPI::class.java)
    }

    fun setupMatchList() {
        binding.rcvMatchs.setHasFixedSize(true)
        binding.rcvMatchs.layoutManager = LinearLayoutManager(this)
        findMatchesFromApi()
        matchesAdapter = MatchesAdapter(Collections.emptyList())
        binding.rcvMatchs.adapter = matchesAdapter
    }

    private fun setupFloatActionButton() {
        binding.fabSimulate.setOnClickListener() {

            var random = Random()

            for (i in 0..matchesAdapter.itemCount - 1) {
                var mat = matchesAdapter.matches.get(i)
                mat.homeTeam.score = random.nextInt(mat.homeTeam.stars + 1)
                mat.awayTeam.score = random.nextInt(mat.awayTeam.stars + 1)
                matchesAdapter.notifyItemChanged(i)
            }
        }
    }

    private fun setupMatchRefresh() {
        binding.srfMatchs.setOnRefreshListener(this::findMatchesFromApi);
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.erro_api, Snackbar.LENGTH_LONG).show()
    }

    private fun findMatchesFromApi() {
        binding.srfMatchs.isRefreshing = true

        matchesAPI.getMatches().enqueue(object : Callback<List<Match>> {

            override fun onResponse(
                call: Call<List<Match>>,
                response: Response<List<Match>>
            ) {
                if (response.isSuccessful) {
                    var matches: List<Match>? = response.body()
                    matchesAdapter = MatchesAdapter(matches)
                    binding.rcvMatchs.adapter = matchesAdapter
                } else {
                    showErrorMessage()
                }
                binding.srfMatchs.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
                binding.srfMatchs.isRefreshing = false
            }
        })

        binding.srfMatchs.isRefreshing = false

    }
}

