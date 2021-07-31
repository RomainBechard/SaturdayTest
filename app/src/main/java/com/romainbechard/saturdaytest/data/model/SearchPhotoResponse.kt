package com.romainbechard.saturdaytest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchPhotoResponse(
    @JsonProperty("total") val nbResults: Int,
    @JsonProperty("totalHits") val nbHits: Int,
    @JsonProperty("hits") val hits: List<Hit>
)