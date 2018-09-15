package groentved.andreas.comeonsquat.ui.settings

import android.app.Application
import groentved.andreas.comeonsquat.SquatApp
import groentved.andreas.comeonsquat.domain.AccelerationUtil
import groentved.andreas.comeonsquat.domain.AccelerationUtil.format
import groentved.andreas.comeonsquat.domain.PhonePosition
import groentved.andreas.comeonsquat.domain.StableTimer
import groentved.andreas.comeonsquat.ui.base.BaseViewModel

/**
 * Created by Andreas Grøntved on 11-09-2018.
 **/

class SettingsViewModel constructor(application: Application) : BaseViewModel(application as SquatApp) {


    private val stableTimer = StableTimer()
    private var lastUpdate = listOf(0.0, 0.0, 0.0)

    fun progressTimer() = stableTimer.timerObservable
    fun endTimer() = stableTimer.endTimeObservable


    fun update() {
        stableTimer.start()
        domain.startAccelerationUpdates(100).subscribe {
            println("update")
            println(lastUpdate)
            val average: List<Double> = AccelerationUtil.getAverage(it)
            lastUpdate = average
            stableTimer.update(average)
        }
    }

    fun updateStartParallelAcceleration(startParallel: Boolean) {
        println(lastUpdate)
        if (startParallel) domain.setStart(lastUpdate[PhonePosition.getAxis(domain.getPhonePosition())].toFloat())
        else domain.setParallel(lastUpdate[PhonePosition.getAxis(domain.getPhonePosition())].toFloat())
    }

    fun stop() {
        domain.stopOrientationUpdates()
    }


    fun setVolume(percentage: Float) {
        domain.setVolume(percentage / 100f)
    }

    fun setError(percentage: Float) {
        domain.setOverShootBuffer(percentage)
    }

    fun setPosition(position: Int) {
        val position = PhonePosition.getEnumVal(position)
        domain.setPhonePosition(position)
        domain.setStart(PhonePosition.getAxisDefaultStart(position))
        domain.setParallel(PhonePosition.getAxisDefaultParallel(position))
    }

    fun getVolume(): Int = (domain.getVolume() * 100).toInt()
    fun getError(): Float = domain.getOverShootBuffer()
    fun getPosition(): Int = domain.getPhonePosition().phoneVal

    fun formatTimeLeft(float: Float) = "${float.toDouble().format(1)}%"


}