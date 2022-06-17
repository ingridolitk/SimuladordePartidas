package com.ingrid.simuladordepartidas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ingrid.simuladordepartidas.databinding.ActivityMainBinding
import com.ingrid.simuladordepartidas.domain.Team
import java.sql.Time

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time: Team = Team("teste", 1, "teste")
        time.image

        setupMatchList()
        setupMatchRefresh()
        setupFloatActionButton()
    }

    private fun setupFloatActionButton() {
        TODO("Not yet implemented")
    }

    private fun setupMatchRefresh() {
        TODO("Not yet implemented")
    }

    private fun setupMatchList() {
        TODO("Not yet implemented")
    }
}