package groentved.andreas.soitisnowsquat.inject.component


import dagger.Component
import groentved.andreas.soitisnowsquat.domain.PositionHandler
import groentved.andreas.soitisnowsquat.inject.PerActivity
import groentved.andreas.soitisnowsquat.inject.module.ActivityModule
import groentved.andreas.soitisnowsquat.ui.base.BaseViewModel
import groentved.andreas.soitisnowsquat.ui.main.MainViewModel

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(baseViewModel: BaseViewModel)
    fun providesPositionHandler(): PositionHandler
}
