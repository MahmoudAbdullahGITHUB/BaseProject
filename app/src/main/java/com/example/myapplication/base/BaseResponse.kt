package com.example.myapplication.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.IOException

open class BaseResponse<T>(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("responseCode")
    var statusCode: Int? = null,
    @SerializedName("success")
    var success: Boolean? = null,
    @Expose
    @SerializedName("data")
    var data: T? = null
) {
    companion object {
        fun <T> from(error: IOException): BaseResponse<T> {
            return BaseResponse(error.localizedMessage)
        }

        fun <T> from(error: Exception): BaseResponse<T> {
            return BaseResponse(error.localizedMessage)
        }

        fun <T> from(error: Throwable): BaseResponse<T> {
            return BaseResponse(error.localizedMessage)
        }
    }

    override fun toString(): String {
        return "Message: $message, statusCode: $statusCode Data: ${data.toString()}"
    }
}