package groentved.andreas.comeonsquat.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import groentved.andreas.comeonsquat.SquatApp
import groentved.andreas.comeonsquat.domain.PhonePosition
import groentved.andreas.comeonsquat.domain.PositionHandler
import groentved.andreas.comeonsquat.ui.base.BaseViewModel
import io.reactivex.Observable
import groentved.andreas.comeonsquat.domain.AccelerationUtil.format

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

    fun playSound() {
        TODO()
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

    fun toggleListening() {
        if (isListening) stopListening()
        else startListening()
    }

    private fun startListening() {

        isListening = true
        subscription = domain.startOrientationUpdates(updateRate)
        subscription!!.subscribe {
            val acceleration = positionHandler.getAcceleration(it, domain.getPhonePosition())
            val hasCrossedParallel = positionHandler.hasCrossedParallel(acceleration, domain.getOverShootBuffer(), domain.getParallel(), PhonePosition.getAxisDefaultStart(domain.getPhonePosition()))
            val stateChanged = hasAboveOrBelowMiddlePointChanged(positionHandler.crossed50Percent(acceleration, domain.getOverShootBuffer(), domain.getParallel()))
            if (stateChanged) handleStateChange(hasCrossedParallel)

            angleUpdates.value = acceleration.format(1)//TODO
        }

    }


    private fun stopListening() {
        isListening = false
        domain.stopOrientationUpdates()
        subscription = null
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




}