class AddPremiumAirConditioning(
    private val carFactory: CarFactory
)  : CarFactory {
    override fun createCarWithManualGearbox(year: Int): Car {
        val originalCar = carFactory.createCarWithManualGearbox(year)
        val carWithEnforcedSetup = Car.CarBuilder(
            originalCar.year,
            originalCar.brand,
            originalCar.gearbox
        )
            .setAirConditioning("Premium air conditioning")

        originalCar.getAirbags().forEach {
            carWithEnforcedSetup.addAirbag(it)
        }
        return carWithEnforcedSetup.build()    }

    override fun createCarWithAutoGearbox(year: Int): Car {
        TODO("Not yet implemented")
    }
}
