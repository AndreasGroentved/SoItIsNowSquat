package groentved.andreas.soitisnowsquat.domain

import groentved.andreas.soitisnowsquat.data.Data

class Domain(private val data: Data) {

    fun isFirstTime(): Boolean = data.isFirstTime()
    fun setIsFirstTime(firstTime: Boolean): Unit = data.setIsFirstTime(firstTime)

}