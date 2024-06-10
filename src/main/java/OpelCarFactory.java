public class OpelCarFactory implements CarFactory {

    @Override
    public Car createCarWithManualGearbox(int year) {
        return new Car.CarBuilder(2022, "Opel", Gearbox.MANUAL)
                .build();
    }

    public Car createCarWithManualGearboxAndDefaultAirbags(int year) {
        return new Car.CarBuilder(2022, "Opel", Gearbox.MANUAL)
                .addAirbag("Default")
                .build();
    }

    @Override
    public Car createCarWithAutoGearbox(int year) {
        return new Car.CarBuilder(2022, "Opel", Gearbox.AUTOMATIC)
                .build();
    }
}
