package groentved.andreas.comeonsquat.ui.settings

import groentved.andreas.comeonsquat.SquatApp
import groentved.andreas.comeonsquat.domain.AccelerationUtil
import groentved.andreas.comeonsquat.domain.AccelerationUtil.format
import groentved.andreas.comeonsquat.domain.PhonePosition
import groentved.andreas.comeonsquat.domain.StableTimer
import groentved.andreas.comeonsquat.ui.base.BaseViewModel

/**
 * Created by Andreas Grøntved on 11-09-2018.
 **/

class SettingsViewModel constructor(application: SquatApp) : BaseViewModel(application) {


    private val stableTimer = StableTimer()


    fun progressTimer() = stableTimer.timerObservable
    fun endTimer() = stableTimer.timerObservable


    fun update() {
        domain.startOrientationUpdates(100).subscribe {
            stableTimer.update(AccelerationUtil.getAverage(it, getPosition()).toFloat())
        }
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