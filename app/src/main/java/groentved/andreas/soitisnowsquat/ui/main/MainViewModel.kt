package groentved.andreas.soitisnowsquat.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import groentved.andreas.soitisnowsquat.SquatApp
import groentved.andreas.soitisnowsquat.domain.PositionHandler
import groentved.andreas.soitisnowsquat.ui.base.BaseViewModel
import io.reactivex.Observable

/**
 * Created by Andreas Grøntved on 12-07-2017.
 **/

class MainViewModel constructor(application: SquatApp) : BaseViewModel(application) {
    private val angleUpdates = MediatorLiveData<String>()
    private val crossedParallel = MediatorLiveData<Boolean>()
    private var isListening = false
    private var updateRate = 100 //TODO setting for ændring af værdi - strøm og sådan noget...
    private var subscription: Observable<List<FloatArray>>? = null
    private val positionHandler: PositionHandler
    private var isBelow50Percent = false
    private var isParallel = false

    init {
        positionHandler = domain.getPositionHandler()
    }


    fun getAngleUpdates(): LiveData<String> = angleUpdates
    fun getParallelUpdates(): LiveData<Boolean> = crossedParallel

    fun isFirstTime(): Boolean =
            if (domain.isFirstTime()) {
                AsyncTask.execute {
                    domain.setIsFirstTime(false)
                }
                true
            } else false

    fun startListening() {
        if (!isListening) {
            isListening = true
            subscription = domain.startOrientationUpdates(updateRate)
            subscription!!.subscribe {
                val acceleration = positionHandler.getAcceleration(it, domain.getPhonePosition())
                val hasCrossedParallel = positionHandler.hasCrossedParallel(acceleration, domain.getPhonePosition(), domain.getOverShootBuffer(), domain.getParallel())
                val stateChanged = hasAboveOrBelowMiddlePointChanged(positionHandler.crossed50Percent(acceleration, domain.getPhonePosition(), domain.getOverShootBuffer(), domain.getParallel()))
                if (stateChanged) handleStateChange(hasCrossedParallel)

                angleUpdates.value = acceleration.format(1)//TODO
            }
        }
    }

    private fun handleStateChange(hasCrossedParallel: Boolean) {
        if (hasCrossedParallel && !isParallel) {
            crossedParallel.value = true
            isParallel = true
        }
        if (!isBelow50Percent) {
            crossedParallel.value = false
            isParallel = false
        }
    }

    private fun hasAboveOrBelowMiddlePointChanged(isNowBelow50Percent: Boolean): Boolean {
        return if (isNowBelow50Percent && !isBelow50Percent || !isNowBelow50Percent && isBelow50Percent) {
            isBelow50Percent = isNowBelow50Percent
            true
        } else false
    }


    private fun Double.format(digits: Int) = String.format("%.${digits}f", this)

}