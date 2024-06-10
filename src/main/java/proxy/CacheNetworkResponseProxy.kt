package proxy

class CacheNetworkResponseProxy(
    private val actualApi: NetworkApi
) : NetworkApi {

    private var cachedData: String = ""

    override fun getDataFromNetwork(request: Request): String {
        if(cachedData.isEmpty()) {
            println("Cached data before call $cachedData")
            cachedData = actualApi.getDataFromNetwork(request)
            println("Cached data after call $cachedData")
        }
        return cachedData
    }

    override fun posDataToNetwork(request: Request, data: String) {
        actualApi.posDataToNetwork(request, data)
    }
}
