package groentved.andreas.soitisnowsquat.inject.component


import android.support.v7.app.AppCompatActivity
import groentved.andreas.soitisnowsquat.inject.PerActivity
import groentved.andreas.soitisnowsquat.inject.module.ActivityModule
import dagger.Component
import groentved.andreas.soitisnowsquat.ui.main.MainActivity

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(appCompatActivity: AppCompatActivity)
    fun inject(mainActivity: MainActivity)
}
