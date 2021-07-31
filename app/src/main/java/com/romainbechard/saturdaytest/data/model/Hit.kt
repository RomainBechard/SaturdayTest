package com.romainbechard.saturdaytest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Hit(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("pageURL") val pageUrl: String?,
    @JsonProperty("type") val type: String?,
    @JsonProperty("tags") val tags: String?,
    @JsonProperty("previewURL") val previewUrl: String?,
    @JsonProperty("previewWidth") val previewWidth: Int?,
    @JsonProperty("previewHeight") val previewHeight: Int?,
    @JsonProperty("webformatURL") val webdormatUrl: String?,
    @JsonProperty("webformatWidth") val webformatWidth: Int?,
    @JsonProperty("webformatHeight") val webformatHeight: Int?,
    @JsonProperty("largeImageURL") val largeImageUrl: String?,
    @JsonProperty("fullHDURL") val fullHdUrl: String?,
    @JsonProperty("imageURL") val imageUrl: String?,
    @JsonProperty("imageWidth") val imageWidth: Int?,
    @JsonProperty("imageHeight") val imageHeight: Int?,
    @JsonProperty("imageSize") val imageSize: Long?,
    @JsonProperty("views") val nbViews: Int?,
    @JsonProperty("downloads") val nbDownloads: Int?,
    @JsonProperty("collections") val collections: Int?,
    @JsonProperty("likes") val nbLikes: Int?,
    @JsonProperty("comments") val nbComments: Int?,
    @JsonProperty("user_id") val userId: Long?,
    @JsonProperty("user") val user: String?,
    @JsonProperty("userImageURL") val userImageUrl: String?
)
