package com.example.stockapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var stockApi: StockApi
    private val apiKey = "6IFRPFHZ2RR1UZH3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.alphavantage.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        stockApi = retrofit.create(StockApi::class.java)

        val searchButton: Button = findViewById(R.id.searchButton)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)

        searchButton.setOnClickListener {
            val stockSymbol = findViewById<EditText>(R.id.stockSymbolInput).text.toString().trim()
            if (stockSymbol.isNotEmpty()) {
                searchStock(stockSymbol, progressBar)
            } else {
                Toast.makeText(this@MainActivity, "Please enter a stock symbol", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun searchStock(symbol: String, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        findViewById<Button>(R.id.searchButton).isEnabled = false

        lifecycleScope.launch {
            try {
                val response = stockApi.getStockData("GLOBAL_QUOTE", symbol, apiKey)
                if (response.isSuccessful) {
                    val stockResponse = response.body()
                    stockResponse?.let {
                        findViewById<TextView>(R.id.companyName).text = "Company Name: ${it.globalQuote.symbol}"
                        findViewById<TextView>(R.id.stockPrice).text = "Stock Price: ${it.globalQuote.price}"
                        findViewById<TextView>(R.id.percentageChange).text = "Percentage Change: ${it.globalQuote.changePercent}"
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                progressBar.visibility = View.GONE
                findViewById<Button>(R.id.searchButton).isEnabled = true
            }
        }
    }
}
