class EnforceDefaultAirbagsCarFactoryDecorator(
    private val carFactory: CarFactory
) : CarFactory {
    override fun createCarWithManualGearbox(year: Int): Car {
        val originalCar =carFactory.createCarWithManualGearbox(year)
        val carWithEnforcedSetup = Car.CarBuilder(
            originalCar.year,
            originalCar.brand,
            originalCar.gearbox
        )
            .setAirConditioning(originalCar.airConditioning)
            .addAirbag("Some default airbags")
        return carWithEnforcedSetup.build()
    }

    override fun createCarWithAutoGearbox(year: Int): Car {
        TODO("Not yet implemented")
    }
}
