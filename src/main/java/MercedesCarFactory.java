public class MercedesCarFactory implements CarFactory {
    @Override
    public Car createCarWithManualGearbox(int year) {
        return new Car.CarBuilder(2022, "Mercedes", Gearbox.MANUAL)
                .setYear(1999)
                .setAirConditioning("Some other air conditioning")
                .addAirbag("Front1")
                .addAirbag("Front2")
                .addAirbag("Left")
                .build();
    }

    @Override
    public Car createCarWithAutoGearbox(int year) {
        return new Car.CarBuilder(2022, "Mercedes", Gearbox.AUTOMATIC)
                .build();
    }
}
