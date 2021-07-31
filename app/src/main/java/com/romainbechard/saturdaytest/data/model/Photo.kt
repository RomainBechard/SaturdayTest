package com.romainbechard.saturdaytest.data.model

data class Photo(
    val imageUrl: String?,
    val fullHdUrl: String?,
    val nbLikes: Int?,
    val nbComents: Int?,
    val nbViews: Int?,
    val hitId: Long?,
    var isSelected: Boolean = false
)
