package groentved.andreas.comeonsquat.inject.module

import android.app.Application
import android.content.Context
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides
import groentved.andreas.comeonsquat.data.Data
import groentved.andreas.comeonsquat.data.pref.AppPreferencesHelper
import groentved.andreas.comeonsquat.data.pref.PrefHelper
import groentved.andreas.comeonsquat.domain.Domain
import groentved.andreas.comeonsquat.domain.PositionHandler
import groentved.andreas.comeonsquat.domain.sensor.Orientation
import groentved.andreas.comeonsquat.domain.sensor.SensorOrientation
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideContext(): Context = application.applicationContext


    @Singleton
    @Provides
    fun provideSensorManager(context: Context): SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager


    @Singleton
    @Provides
    fun provideDataAccess(pref: PrefHelper): Data = Data(pref)


    @Singleton
    @Provides
    fun providesPositionHandler(): PositionHandler = PositionHandler()


    @Singleton
    @Provides
    fun provideOrientation(sensorManager: SensorManager): Orientation = SensorOrientation(sensorManager)


    @Singleton
    @Provides
    fun provideLogicAccess(data: Data, positionHandler: PositionHandler, orientation: Orientation): Domain = Domain(data, orientation, positionHandler)

    @Singleton
    @Provides
    fun providesPrefHelper(context: Context): PrefHelper = AppPreferencesHelper(context)

}