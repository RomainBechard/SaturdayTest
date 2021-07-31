package com.romainbechard.saturdaytest.data

import com.romainbechard.saturdaytest.data.model.Photo
import com.romainbechard.saturdaytest.data.tools.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MyRepository(
    private val api: MyApi
) {

    private val dispatcher = Dispatchers.IO

    suspend fun getSearchResult(string: String): Result<List<Photo>> = withContext(dispatcher) {
        return@withContext try {
            val result = api.searchPhotos(keywords = string)
            val list: MutableList<Photo> = mutableListOf()
            result.hits.forEach {
                list.add(
                    Photo(
                        imageUrl = it.largeImageUrl,
                        fullHdUrl = it.fullHdUrl,
                        nbLikes = it.nbLikes,
                        nbComents = it.nbComments,
                        nbViews = it.nbViews,
                        hitId = it.id
                    )
                )
            }
            Result.Success(list)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

}