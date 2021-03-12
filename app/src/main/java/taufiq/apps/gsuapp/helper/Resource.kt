package taufiq.apps.gsuapp.helper

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null,message)
}