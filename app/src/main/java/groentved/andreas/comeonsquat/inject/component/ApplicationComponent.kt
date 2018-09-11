package groentved.andreas.comeonsquat.inject.component

import android.app.Application
import android.content.Context
import groentved.andreas.comeonsquat.inject.module.ActivityModule
import groentved.andreas.comeonsquat.inject.module.ApplicationModule
import dagger.Component
import groentved.andreas.comeonsquat.SquatApp
import groentved.andreas.comeonsquat.data.Data
import groentved.andreas.comeonsquat.data.pref.PrefHelper
import groentved.andreas.comeonsquat.domain.Domain
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
