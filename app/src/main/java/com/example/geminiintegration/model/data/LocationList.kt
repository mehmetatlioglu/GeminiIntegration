package com.example.geminiintegration.model.data

data class LocationList(
    val info: Info,
    val results: List<LocationItem>,
)

data class LocationInfo(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any?,
)

data class LocationItem(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)
