package groentved.andreas.soitisnowsquat.domain

import groentved.andreas.soitisnowsquat.data.Data
import groentved.andreas.soitisnowsquat.domain.sensor.Orientation
import io.reactivex.Observable

class Domain(private val data: Data, private val orientationSensor: Orientation, private val positionHandler: PositionHandler) {

    fun isFirstTime(): Boolean = data.isFirstTime()
    fun setIsFirstTime(firstTime: Boolean): Unit = data.setIsFirstTime(firstTime)
    fun setParallel(offset: Float) = data.setParallel(offset)
    fun getParallel(): Float = data.getParallel()
    fun setPhonePosition(position: PhonePosition) = data.setPhonePosition(position)
    fun getPhonePosition(): PhonePosition = data.getPhonePosition()
    fun setVolume(volume: Float) = data.setVolume(volume)
    fun getVolume(): Float = data.getVolume()
    fun setOverShootBuffer(percent: Float) = data.setOverShootBuffer(percent)
    fun getOverShootBuffer(): Float = data.getOverShootBuffer()
    fun getPositionHandler() = positionHandler

    fun startOrientationUpdates(delayInMilliseconds: Int): Observable<List<FloatArray>> =
            orientationSensor.start(delayInMilliseconds)

    fun stopOrientationUpdates() = orientationSensor.stop()
}