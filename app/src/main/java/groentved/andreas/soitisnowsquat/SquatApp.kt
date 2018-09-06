package groentved.andreas.soitisnowsquat

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import groentved.andreas.soitisnowsquat.domain.Domain
import groentved.andreas.soitisnowsquat.inject.component.ApplicationComponent
import groentved.andreas.soitisnowsquat.inject.component.DaggerApplicationComponent
import groentved.andreas.soitisnowsquat.inject.module.ApplicationModule
import timber.log.Timber
import javax.inject.Inject

class SquatApp : Application() {

    lateinit var component: ApplicationComponent

    @Inject
    lateinit var domain: Domain


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build().apply { inject(this@SquatApp) }
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())


    }
}
