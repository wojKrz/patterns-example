import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
public class Car {

    private final String id;
    private final int year;
    private final String brand;
    private final Gearbox gearbox;
    private final String airConditioning;
    final List<String> airbags;

    private Car(String id, int year, String brand, Gearbox gearbox, String airConditioning, List<String> airbags) {
        this.id = id;
        this.year = year;
        this.brand = brand;
        this.gearbox = gearbox;
        this.airConditioning = airConditioning;
        this.airbags = airbags;
    }

    static class CarBuilder {
        private int year;
        private String brand;
        private Gearbox gearbox;
        private String airConditioning = "";
        private List<String> airbags = new ArrayList<>();

        public CarBuilder(int year, String brand, Gearbox gearbox) {
            this.year = year;
            this.brand = brand;
            this.gearbox = gearbox;
        }

        public CarBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public CarBuilder setAirConditioning(String airConditioning) {
            this.airConditioning = airConditioning;
            return this;
        }

        public CarBuilder addAirbag(String airbag) {
            this.airbags.add(airbag);
            return this;
        }

        Car build() {
            return new Car(
                    UUID.randomUUID().toString(),
                    year, brand, gearbox, airConditioning,
                    Collections.unmodifiableList(airbags)
            );
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                ", gearbox=" + gearbox +
                ", airConditioning='" + airConditioning + '\'' +
                ", airbags='" + airbags + '\'' +
                '}';
    }
}

