package proxy

class AttachAuthDataProxy(
    private val actualApi: NetworkApi
) : NetworkApi {
    override fun getDataFromNetwork(request: Request): String {
        request.attachAuthData("12fdsannh1319023")
        return actualApi.getDataFromNetwork(request)
    }

    override fun posDataToNetwork(request: Request, data: String) {
        request.attachAuthData("12fdsannh1319023")
        actualApi.posDataToNetwork(request, data)
    }

}
