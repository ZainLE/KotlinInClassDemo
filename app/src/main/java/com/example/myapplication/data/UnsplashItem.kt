package com.example.myapplication.data

data class UnsplashItem(
    val blur_hash: String?,
    val color: String?,
    val created_at: String?,
    val current_user_collections: List<CurrentUserCollection?>?,
    val description: String?,
    val height: Int?,
    val id: String?,
    val liked_by_user: Boolean?,
    val likes: Int?,
    val links: Links?,
    val updated_at: String?,
    val urls: Urls?,
    val user: User?,
    val width: Int?,
    // Add these fields:
    val exif: Exif?,
    val location: Location?,
    val tags: List<Tag>?,
    val downloads: Int?,
    val public_domain: Boolean?
)

data class Exif(
    val make: String?,
    val model: String?,
    val name: String?,
    val exposure_time: String?,
    val aperture: String?,
    val focal_length: String?,
    val iso: Int?
)

data class Location(
    val city: String?,
    val country: String?,
    val position: Position?
)

data class Position(
    val latitude: Double?,
    val longitude: Double?
)

data class Tag(
    val title: String?
)