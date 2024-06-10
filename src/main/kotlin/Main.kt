import proxy.*

fun main(args: Array<String>) {

    val airConditioningEnforcer = when (args.firstOrNull()) {
        "enforce" -> {
            { original: CarFactory ->
                AddPremiumAirConditioning(original)
            }
        }

        else -> {
            { original: CarFactory -> original }
        }
    }

    val invoice = Invoice(
        "John", "fdsasdf", "Elm Street 10", "942349", 2000, "USD"
    )

    Validator(
        listOf(
            FieldIsNotEmptyRule(Invoice::firstName, "firstName"),
            FieldIsNotEmptyRule(Invoice::secondName, "secondName"),
            FieldIsNotEmptyRule(
                Invoice::taxIdNumber,
                "taxIdNumber",
                ObeysTaxIdFormatRule(Invoice::taxIdNumber)
            )
        )
    ).validate(invoice)

    val mercedesCarFactory: CarFactory = MercedesCarFactory()
    val opelCarFactory: CarFactory = OpelCarFactory().run { EnforceDefaultAirbagsCarFactoryDecorator(this) }


    val carFactoriesList = listOf(mercedesCarFactory, opelCarFactory)
        .map { airConditioningEnforcer(it) }

    val cars = carFactoriesList.map { it.createCarWithManualGearbox(2022) }

    val api: NetworkApi = ActualNetworkApi().run(::AttachAuthDataProxy).run(::CacheNetworkResponseProxy)

    println(
        api.getDataFromNetwork(
            Request
        )
    )

    println(
        api.posDataToNetwork(
            Request,
            "some data to post"
        )
    )
}
