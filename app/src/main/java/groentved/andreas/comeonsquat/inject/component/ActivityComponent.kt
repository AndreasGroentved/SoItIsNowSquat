package groentved.andreas.comeonsquat.inject.component


import dagger.Component
import groentved.andreas.comeonsquat.domain.PositionHandler
import groentved.andreas.comeonsquat.inject.PerActivity
import groentved.andreas.comeonsquat.inject.module.ActivityModule
import groentved.andreas.comeonsquat.ui.base.BaseViewModel
import groentved.andreas.comeonsquat.ui.main.MainViewModel

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(baseViewModel: BaseViewModel)
}
