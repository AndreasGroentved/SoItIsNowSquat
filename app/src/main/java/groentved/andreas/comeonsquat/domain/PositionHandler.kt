package groentved.andreas.comeonsquat.domain

import groentved.andreas.comeonsquat.domain.PhonePosition.*

class PositionHandler {

    fun getAcceleration(accelerationArray: List<List<Float>>, phonePosition: PhonePosition): Double {
        return when (phonePosition) {
            BACK_PARALLEL_TO_TOP_OF_QUAD -> AccelerationUtil.getAverageOneAxis(accelerationArray, 1)
            BACK_PARALLEL_TO_SIDE_OF_QUAD -> AccelerationUtil.getAverageOneAxis(accelerationArray, 0)
            BACK_PERPENDICULAR_TO_SIDE_OF_QUAD -> AccelerationUtil.getAverageOneAxis(accelerationArray, 0)
            BACK_PERPENDICULAR_TO_TOP_OF_QUAD -> AccelerationUtil.getAverageOneAxis(accelerationArray, 3)
        }
    }

    fun getSquatPercentage(acceleration: Double, overShootBuffer: Float, parallelAcceleration: Float, startAcceleration: Float): Double =
            if (parallelAcceleration < startAcceleration) (parallelAcceleration - acceleration) / multiplyBuffer(overShootBuffer, parallelAcceleration, startAcceleration)
            else (acceleration - startAcceleration) / multiplyBuffer(overShootBuffer, parallelAcceleration, startAcceleration)


    fun crossed50Percent(acceleration: Double, parallelAcceleration: Float, startAcceleration: Float): Boolean {
        val half = (parallelAcceleration - startAcceleration) / 2
        val percentMoved = acceleration / half
        return percentMoved >= 1
    }

    fun hasCrossedParallel(acceleration: Double, overShootBuffer: Float, parallelAcceleration: Float, startAcceleration: Float): Boolean {
        val parallelVal = multiplyBuffer(overShootBuffer, parallelAcceleration, startAcceleration)
        return if (parallelAcceleration < startAcceleration && acceleration < parallelVal) true
        else if (parallelAcceleration > startAcceleration && acceleration > parallelVal) true
        else false
    }

    fun multiplyBuffer(overShootBuffer: Float, parallelAcceleration: Float, startAcceleration: Float): Float =
            if (parallelAcceleration < startAcceleration) startAcceleration - ((startAcceleration - parallelAcceleration) * overShootBuffer)
            else startAcceleration + (parallelAcceleration - startAcceleration) * overShootBuffer


}