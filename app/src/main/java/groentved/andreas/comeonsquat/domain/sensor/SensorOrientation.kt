package groentved.andreas.comeonsquat.domain.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class SensorOrientation(sensorManager: SensorManager) : SensorEventListener, Orientation {

    private val sensorManager: SensorManager
    private val sensorAccelerometer: Sensor

    private var valuesAccelerometer = listOf<Float>()
    private var publishSubject: PublishSubject<List<Float>>? = null

    init {
        this.sensorManager = sensorManager
        this.sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun start(delayInMilliseconds: Int): Observable<List<List<Float>>> {
        if (publishSubject == null) publishSubject = PublishSubject.create()
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_GAME)
        return publishSubject!!.buffer(delayInMilliseconds.toLong(), TimeUnit.MILLISECONDS)
    }

    override fun stop() {
        sensorManager.unregisterListener(this, sensorAccelerometer)
        if (publishSubject != null) publishSubject!!.onComplete()
        publishSubject = null
    }

    override fun onAccuracyChanged(arg0: Sensor, arg1: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (canPublish()) {
            val i = event.sensor.type
            if (i == Sensor.TYPE_ACCELEROMETER) {
                valuesAccelerometer = event.values.copyOfRange(0, 3).toList()
                println("come on son: " + valuesAccelerometer.toString())
                publishSubject!!.onNext(valuesAccelerometer)
            }
        }
    }


    private fun canPublish(): Boolean = publishSubject != null

}