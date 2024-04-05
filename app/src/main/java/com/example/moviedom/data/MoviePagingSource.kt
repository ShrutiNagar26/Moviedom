package com.example.moviedom.data



import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

 class MoviePagingSource (private val service: MovieApi,
    private val searchedTerm:String
) : PagingSource<Int, MovieDetails>() {
     private var feedsDetails: List<MovieDetails> = ArrayList()

     override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        val pageIndex = params.key ?: 1
        return try {
            val response = service.searchMovies(
                search = searchedTerm,
                page = pageIndex
            )

            // Need to check if results are not null to avoid crash
            if (response.results != null) {
                 feedsDetails = response.results
            }else{
                feedsDetails = emptyList()
            }

            //Update next key only if response is not empty
            val nextKey = if (feedsDetails.isEmpty()) {
                null
            } else {
                pageIndex + 1
            }
            LoadResult.Page(
                data = feedsDetails,
                prevKey = if (pageIndex == 1) null else (pageIndex - 1),
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

     /**
      * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
      */
     override fun getRefreshKey(state: PagingState<Int, MovieDetails>): Int? {
         return state.anchorPosition?.let { anchorPosition ->
             state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                 ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
         }
     }


}