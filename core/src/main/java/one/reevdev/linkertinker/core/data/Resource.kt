package one.reevdev.linkertinker.core.data

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
    data class Failure<T>(
        val message: String,
        val exception: Exception? = null,
        val data: T? = null
    ) : Resource<T>()
}