package groentved.andreas.comeonsquat.domain

import groentved.andreas.comeonsquat.data.Data
import groentved.andreas.comeonsquat.domain.sensor.Orientation
import groentved.andreas.comeonsquat.domain.sound.SoundPlayer
import io.reactivex.Observable

class Domain(private val data: Data, private val orientationSensor: Orientation, private val positionHandler: PositionHandler, private val sound: SoundPlayer) {

    fun isFirstTime(): Boolean = data.isFirstTime()
    fun setIsFirstTime(firstTime: Boolean): Unit = data.setIsFirstTime(firstTime)
    fun setParallel(offset: Float) = data.setParallel(offset)
    fun getParallel(): Float = data.getParallel()
    fun setStart(offset: Float) = data.setStart(offset)
    fun getStart(): Float = data.getStart()
    fun setPhonePosition(position: PhonePosition) = data.setPhonePosition(position)
    fun getPhonePosition(): PhonePosition = data.getPhonePosition()
    fun setVolume(volume: Float) = data.setVolume(volume)
    fun getVolume(): Float = data.getVolume()
    fun setOverShootBuffer(percent: Float) = data.setOverShootBuffer(percent)
    fun getOverShootBuffer(): Float = data.getOverShootBuffer()
    fun getPositionHandler() = positionHandler
    fun getSound(): SoundPlayer = sound

    fun startAccelerationUpdates(delayInMilliseconds: Int): Observable<List<List<Float>>> =
            orientationSensor.start(delayInMilliseconds)

    fun stopOrientationUpdates() = orientationSensor.stop()
}