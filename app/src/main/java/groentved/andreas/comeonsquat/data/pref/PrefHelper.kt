package groentved.andreas.comeonsquat.data.pref

import groentved.andreas.comeonsquat.domain.PhonePosition


interface PrefHelper {
    fun getIsFirstTime(): Boolean
    fun setIsFirstTime(firstTime: Boolean)

    fun setParallel(offset: Float)
    fun getParallel(): Float

    fun setStart(offset: Float)
    fun getStart(): Float

    fun setOverShootBuffer(percent: Float)
    fun getOverShootBuffer(): Float

    fun setPhonePosition(position: PhonePosition)
    fun getPhonePosition(): PhonePosition

    fun setVolume(volume: Float)
    fun getVolume(): Float
}