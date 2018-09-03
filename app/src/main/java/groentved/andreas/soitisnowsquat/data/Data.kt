package groentved.andreas.soitisnowsquat.data

import groentved.andreas.soitisnowsquat.data.pref.PrefHelper
import javax.inject.Inject

class Data @Inject constructor(private val pref: PrefHelper) {

    fun setIsFirstTime(isFirstTime: Boolean): Unit = pref.setIsFirstTime(isFirstTime)
    fun isFirstTime(): Boolean = pref.getIsFirstTime()

}