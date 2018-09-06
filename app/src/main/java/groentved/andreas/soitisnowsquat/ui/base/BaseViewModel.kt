package groentved.andreas.soitisnowsquat.ui.base

import android.arch.lifecycle.AndroidViewModel
import groentved.andreas.soitisnowsquat.SquatApp
import groentved.andreas.soitisnowsquat.domain.Domain
import groentved.andreas.soitisnowsquat.inject.component.DaggerActivityComponent
import groentved.andreas.soitisnowsquat.inject.module.ActivityModule
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