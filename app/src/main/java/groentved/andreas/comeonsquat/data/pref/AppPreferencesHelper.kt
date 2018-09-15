package groentved.andreas.comeonsquat.data.pref

import android.content.Context
import android.content.SharedPreferences
import groentved.andreas.comeonsquat.domain.PhonePosition

class AppPreferencesHelper(context: Context) : PrefHelper {

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    }

    override fun getIsFirstTime(): Boolean = pref.getBoolean(PREF_IS_FIRST_TIME, true)
    override fun setIsFirstTime(firstTime: Boolean) = pref.edit().putBoolean(PREF_IS_FIRST_TIME, firstTime).apply()
    override fun setParallel(offset: Float) = pref.edit().putFloat(PREF_PARALLEL, offset).apply()
    override fun getParallel(): Float = pref.getFloat(PREF_PARALLEL, 0f)
    override fun setStart(offset: Float) = pref.edit().putFloat(PREF_START, offset).apply()
    override fun getStart(): Float = pref.getFloat(PREF_START, 0f)
    override fun setOverShootBuffer(percent: Float) = pref.edit().putFloat(PREF_BUFFER, percent).apply()
    override fun getOverShootBuffer(): Float = pref.getFloat(PREF_BUFFER, 0f)
    override fun setPhonePosition(position: PhonePosition) = pref.edit().putInt(PREF_POSITION, position.phoneVal).apply()
    override fun getPhonePosition(): PhonePosition = PhonePosition.getEnumVal(pref.getInt(PREF_POSITION, 0))
    override fun setVolume(volume: Float) = pref.edit().putFloat(PREF_VOLUME, volume).apply()
    override fun getVolume(): Float = pref.getFloat(PREF_VOLUME, 1f)

    companion object {
        @JvmStatic
        private val PREF_IS_FIRST_TIME = "FIRST_TIME"
        @JvmStatic
        private val PREF_PARALLEL = "PAR"
        @JvmStatic
        private val PREF_START = "START"
        @JvmStatic
        private val PREF_BUFFER = "BUF"
        @JvmStatic
        private val PREF_POSITION = "POS"
        @JvmStatic
        private val PREF_VOLUME = "VOL"
        @JvmStatic
        private val PREF = "SQUAT"

    }
}