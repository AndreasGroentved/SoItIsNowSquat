package groentved.andreas.soitisnowsquat.inject.module

import android.app.Application
import android.content.Context
import com.groentved.andreas.chordrecognition.data.pref.AppPreferencesHelper
import dagger.Module
import dagger.Provides
import groentved.andreas.soitisnowsquat.data.Data
import groentved.andreas.soitisnowsquat.data.pref.PrefHelper
import groentved.andreas.soitisnowsquat.domain.Domain
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
    fun provideDataAccess(pref: PrefHelper): Data = Data(pref)

    @Singleton
    @Provides
    fun provideLogicAccess(data: Data): Domain = Domain(data)

    @Singleton
    @Provides
    fun providesPrefHelper(context: Context): PrefHelper = AppPreferencesHelper(context)

}