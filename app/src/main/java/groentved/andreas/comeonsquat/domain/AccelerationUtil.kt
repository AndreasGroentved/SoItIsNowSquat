package groentved.andreas.comeonsquat.domain

object AccelerationUtil {
    fun getAverageOneAxis(accelerationArray: List<List<Float>>, axis: Int): Double =
            accelerationArray.asSequence().map { it[axis] }.average()

    fun getAverage(accelerationArray: List<List<Float>>): List<Double> {
        val xArrayAvg = accelerationArray.asSequence().map { it[0] }.average()
        val yArrayAvg = accelerationArray.asSequence().map { it[1] }.average()
        val zArrayAvg = accelerationArray.asSequence().map { it[2] }.average()
        return listOf(xArrayAvg, yArrayAvg, zArrayAvg)
    }

    fun Double.format(digits: Int) = String.format("%.${digits}f", this)
}