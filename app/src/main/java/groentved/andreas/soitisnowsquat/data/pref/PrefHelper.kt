package groentved.andreas.soitisnowsquat.data.pref


interface PrefHelper {
    fun getIsFirstTime(): Boolean
    fun setIsFirstTime(firstTime: Boolean)
}