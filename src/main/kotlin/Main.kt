fun main(args: Array<String>) {

    val carFactory: CarFactory = if (args.first() == "Mercedes") {
        MercedesCarFactory()
    } else {
        OpelCarFactory();
    }

    println(SingleInstanceExample.getInstance().i)
    SingleInstanceExample.getInstance().increaseI()

    println(SingleInstanceExample.getInstance().i)

    println(SingleInstanceExample.getInstance().carFactory.createCarWithAutoGearbox(2200))
}
