package groentved.andreas.soitisnowsquat.domain

class PositionHandler {


    fun getAcceleration(accelArray: List<FloatArray>, phonePosition: PhonePosition): Double {
        val acceleration: Double = when (phonePosition) {
            PhonePosition.BACK_PARALLEL_TO_TOP_OF_QUAD -> getAverage(accelArray, 1)
            PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD -> getAverage(accelArray, 0)
            PhonePosition.BACK_PERPENDICULAR_TO_SIDE_OF_QUAD -> getAverage(accelArray, 0)
            PhonePosition.BACK_PERPENDICULAR_TO_TOP_OF_QUAD -> getAverage(accelArray, 3)
        }

        return acceleration
    }

    fun crossed50Percent(acceleration: Double, phonePosition: PhonePosition, overShootBuffer: Float, parrallelAcceleration: Float):Boolean = TODO()

    fun hasCrossedParallel(acceleration: Double, phonePosition: PhonePosition, overShootBuffer: Float, parrallelAcceleration: Float): Boolean = TODO()

    private fun getAverage(accelArray: List<FloatArray>, axis: Int): Double =
            accelArray.map { it[axis] }.average()
}