class SpaceAge(private val seconds: Long) {

    // TODO: Implement proper constructor

    companion object {
        private const val EARTH_YEAR_IN_SECONDS = 31557600.0

        private const val MERCURY_ORBITAL_PERIOD = 0.2408467
        private const val VENUS_ORBITAL_PERIOD = 0.61519726
        private const val EARTH_ORBITAL_PERIOD = 1.0
        private const val MARS_ORBITAL_PERIOD = 1.8808158
        private const val JUPITER_ORBITAL_PERIOD = 11.862615
        private const val SATURN_ORBITAL_PERIOD = 29.447498
        private const val URANUS_ORBITAL_PERIOD = 84.016846
        private const val NEPTUNE_ORBITAL_PERIOD = 164.79132
    }

    private fun ageOnPlanet(orbitalPeriod: Double): Double  {
        return seconds / (EARTH_YEAR_IN_SECONDS * orbitalPeriod)
    }

    fun onEarth(): Double = ageOnPlanet(EARTH_ORBITAL_PERIOD)
    fun onMercury(): Double = ageOnPlanet(MERCURY_ORBITAL_PERIOD)
    fun onVenus(): Double = ageOnPlanet(VENUS_ORBITAL_PERIOD)
    fun onMars(): Double = ageOnPlanet(MARS_ORBITAL_PERIOD)
    fun onJupiter(): Double = ageOnPlanet(JUPITER_ORBITAL_PERIOD)
    fun onSaturn(): Double = ageOnPlanet(SATURN_ORBITAL_PERIOD)
    fun onUranus(): Double = ageOnPlanet(URANUS_ORBITAL_PERIOD)
    fun onNeptune(): Double = ageOnPlanet(NEPTUNE_ORBITAL_PERIOD)    
}

fun main() {
    val age = SpaceAge(1_000_000_000)
    print("My age on earth is : ${age.onEarth()}\n")
    print("My age on mercury is : ${age.onMercury()}\n")
    print("My age on venus is : ${age.onVenus()}")
}
