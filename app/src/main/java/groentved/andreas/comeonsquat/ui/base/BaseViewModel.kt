package groentved.andreas.comeonsquat.ui.base

import android.arch.lifecycle.AndroidViewModel
import groentved.andreas.comeonsquat.SquatApp
import groentved.andreas.comeonsquat.domain.Domain
import groentved.andreas.comeonsquat.inject.component.DaggerActivityComponent
import groentved.andreas.comeonsquat.inject.module.ActivityModule
import javax.inject.Inject

/**
 * Created by Andreas Grøntved on 05-08-2017.
 **/

abstract class BaseViewModel(application: SquatApp) : AndroidViewModel(application) {

    @Inject
    lateinit var domain: Domain

    init {
        DaggerActivityComponent.builder().applicationComponent(application.component).activityModule(ActivityModule()).build().inject(this)
    }

}