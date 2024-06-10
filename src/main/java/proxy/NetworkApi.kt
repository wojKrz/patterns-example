package proxy

interface NetworkApi {
    fun getDataFromNetwork(request: Request): String

    fun posDataToNetwork(request: Request, data: String)
}

object Request {
    fun attachAuthData(token: String) {
        println("Attaching token $token")
    }
}
