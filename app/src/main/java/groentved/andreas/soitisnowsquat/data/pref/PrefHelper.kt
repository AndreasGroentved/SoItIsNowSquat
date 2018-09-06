package groentved.andreas.soitisnowsquat.data.pref

import groentved.andreas.soitisnowsquat.domain.PhonePosition


interface PrefHelper {
    fun getIsFirstTime(): Boolean
    fun setIsFirstTime(firstTime: Boolean)

    fun setParallel(angle: Float)
    fun getParallel(): Float

    fun setOverShootBuffer(percent: Float)
    fun getOverShootBuffer(): Float

    fun setPhonePosition(position: PhonePosition)
    fun getPhonePosition(): PhonePosition

    fun setVolume(volume: Float)
    fun getVolume(): Float
}