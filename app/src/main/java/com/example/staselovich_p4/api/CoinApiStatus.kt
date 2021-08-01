package com.example.staselovich_p4.api

data class CoinApiStatus(
    val data: List<CoinResponse> = emptyList(),
    val status: Status
) {
    data class Status(
        val credit_count: Int,
        val elapsed: Int,
        val error_code: Int,
        val error_message: Any,
        val notice: Any,
        val timestamp: String,
        val total_count: Int
    )
}
