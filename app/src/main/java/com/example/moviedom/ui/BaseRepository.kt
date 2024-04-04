package com.example.moviedom.ui

import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import com.example.moviedom.network.Result

open class BaseRepository {
    private suspend fun <T: Any> safeAPIResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return Result.Success(response.body()!!)
            }else{
                return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
            }
        }catch (ex: SocketTimeoutException) {
            return Result.Error(SocketTimeoutException("Internet Connection Error. Please try again later"))
        }
    }


    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeAPIResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data

    }
}