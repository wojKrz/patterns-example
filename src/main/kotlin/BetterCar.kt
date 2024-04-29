data class BetterCar(
    val id: String,
    val year: Int,
    val brand: String,
    val gearbox: Gearbox,
    val airConditioning: String = "",
    val airbags: List<String>
)
