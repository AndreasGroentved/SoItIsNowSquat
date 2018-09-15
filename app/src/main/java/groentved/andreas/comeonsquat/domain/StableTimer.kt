package groentved.andreas.comeonsquat.domain

import android.arch.lifecycle.MediatorLiveData
import android.os.CountDownTimer


class StableTimer(howLong: Long = StableTimer.howLong) {
    val timerObservable = MediatorLiveData<Float>()
    val endTimeObservable = MediatorLiveData<Boolean>()
    private val countDownTimer: CountDownTimer
    private var d1 = -1.0
    private var d2 = -1.0
    private var d3 = -1.0
    private var isUpdating = false


    init {
        countDownTimer = object : CountDownTimer(howLong, 100) {

            override fun onTick(millisUntilFinished: Long) {
                if (isUpdating.not()) return
                timerObservable.value = (howLong - millisUntilFinished).toFloat() / howLong * 100
            }

            override fun onFinish() {
                if (isUpdating.not()) return
                isUpdating = false
                endTimeObservable.value = true
                cancel()
            }
        }
    }

    fun update(acceleration: List<Double>, unstableMax: Double = unstable) {
        val accelerationD1: Double = acceleration[0]
        val accelerationD2: Double = acceleration.getOrNull(1) ?: -1.0
        val accelerationD3: Double = acceleration.getOrNull(2) ?: -1.0
        val unstable = isUnstable(accelerationD1, accelerationD2, accelerationD3, unstableMax)
        if (unstable) reset()
    }

    private fun isUnstable(accelerationD1: Double, accelerationD2: Double, accelerationD3: Double, unstableMax: Double): Boolean {
        when {
            Math.abs(d1 - accelerationD1) < unstableMax -> {
            }
            accelerationD2 != -1.0 && Math.abs(d2 - accelerationD2) < unstableMax -> {
            }
            accelerationD3 != -1.0 && Math.abs(d3 - accelerationD3) < unstableMax -> {
            }
            else -> {
                updateLastValues(accelerationD1, accelerationD2, accelerationD3)
                println("unstable")
                return true
            }
        }
        updateLastValues(accelerationD1, accelerationD2, accelerationD3)
        println("stable")
        return false
    }

    private fun updateLastValues(accelerationD1: Double, accelerationD2: Double, accelerationD3: Double) {
        d1 = accelerationD1
        d2 = accelerationD2
        d3 = accelerationD3
    }

    private fun reset() {
        println("reset")
        countDownTimer.cancel()
        schedule()
    }

    fun start() {
        isUpdating = true
        reset()
    }

    fun stop() {
        isUpdating = false
        countDownTimer.cancel()
    }


    private fun schedule() {
        countDownTimer.start()
    }


    companion object {
        const val howLong = 3000L
        const val unstable = 0.3
    }
}