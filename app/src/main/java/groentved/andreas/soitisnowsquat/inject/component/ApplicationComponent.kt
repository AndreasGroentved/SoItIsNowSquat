package groentved.andreas.soitisnowsquat.inject.component

import android.app.Application
import android.content.Context
import groentved.andreas.soitisnowsquat.inject.module.ActivityModule
import groentved.andreas.soitisnowsquat.inject.module.ApplicationModule
import dagger.Component
import groentved.andreas.soitisnowsquat.SquatApp
import groentved.andreas.soitisnowsquat.data.Data
import groentved.andreas.soitisnowsquat.data.pref.PrefHelper
import groentved.andreas.soitisnowsquat.domain.Domain
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (ActivityModule::class)])
interface ApplicationComponent {

    fun context(): Context

    fun application(): Application
    fun inject(app: SquatApp)
    fun provideDataAccess(): Data
    fun provideLogicAccess(): Domain
    fun providesPrefHelper(): PrefHelper


}
