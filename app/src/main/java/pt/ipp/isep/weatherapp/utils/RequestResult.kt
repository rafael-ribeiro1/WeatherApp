package pt.ipp.isep.weatherapp.utils

class RequestResult<out T>(val status: RequestStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): RequestResult<T> =
            RequestResult(RequestStatus.SUCCESS, data, null)
        fun <T> error(data: T?, message: String): RequestResult<T> =
            RequestResult(RequestStatus.ERROR, data, message)
    }
}