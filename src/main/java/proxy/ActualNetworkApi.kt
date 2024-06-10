package proxy

class ActualNetworkApi : NetworkApi {
    override fun getDataFromNetwork(request: Request): String = "Data from network"

    override fun posDataToNetwork(request: Request, data: String) {
        println("posted data $data")
    }
}
