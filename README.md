# Stock Market App

A simple Android application that allows users to search for stock information by stock symbols. It fetches real-time stock data such as company name, stock price, and percentage change using the Alpha Vantage API.

## Features

- Search stock by symbol (e.g., AAPL, GOOGL, MSFT)
- Display company name, current stock price, and percentage change
- Loading indicator while fetching data
- User-friendly UI with a clean and responsive design

## Screenshots

<img src="![screenshot](https://github.com/user-attachments/assets/be60e4c0-5465-425c-8273-470ef8a73186)
" alt="Stock Search Screenshot" width="300"/>

## Technologies Used

- **Kotlin**: Used for Android app development
- **Retrofit**: To make network requests to the Alpha Vantage API
- **Coroutines**: For asynchronous API calls
- **Alpha Vantage API**: To fetch stock market data
- **Android Jetpack**: Lifecycle-aware components like `lifecycleScope` for coroutine management

## Requirements

- **Android Studio**: For building and running the app
- **Alpha Vantage API Key**: You need to register and get an API key from [Alpha Vantage](https://www.alphavantage.co/support/#api-key) to fetch stock data

## Setup and Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/stock-market-app.git
    ```

2. Open the project in Android Studio.

3. In the `MainActivity.kt` file, replace the `apiKey` variable with your Alpha Vantage API key.

    ```kotlin
    private val apiKey = "YOUR_API_KEY"  // Replace with your API key
    ```

4. Build and run the app on your Android emulator or physical device.

## Usage

1. Launch the app.
2. Enter a valid stock symbol (e.g., AAPL for Apple Inc., GOOGL for Alphabet Inc.).
3. Tap the "Search" button to fetch the stock information.
4. The app will display the company name, current stock price, and percentage change.

