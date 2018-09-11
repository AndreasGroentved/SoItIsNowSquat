package groentved.andreas.comeonsquat.data

import groentved.andreas.comeonsquat.data.pref.PrefHelper
import groentved.andreas.comeonsquat.domain.PhonePosition
import javax.inject.Inject

class Data @Inject constructor(private val pref: PrefHelper) {

    fun setIsFirstTime(isFirstTime: Boolean): Unit = pref.setIsFirstTime(isFirstTime)
    fun isFirstTime(): Boolean = pref.getIsFirstTime()
    fun setParallel(offset: Float) = pref.setParallel(offset)
    fun getParallel(): Float = pref.getParallel()
    fun setStart(offset: Float) = pref.setStart(offset)
    fun getStart(): Float = pref.getStart()
    fun setPhonePosition(position: PhonePosition) = pref.setPhonePosition(position)
    fun getPhonePosition(): PhonePosition = pref.getPhonePosition()
    fun setVolume(volume: Float) = pref.setVolume(volume)
    fun getVolume(): Float = pref.getVolume()
    fun setOverShootBuffer(percent: Float) = pref.setOverShootBuffer(percent)
    fun getOverShootBuffer(): Float = pref.getOverShootBuffer()

}