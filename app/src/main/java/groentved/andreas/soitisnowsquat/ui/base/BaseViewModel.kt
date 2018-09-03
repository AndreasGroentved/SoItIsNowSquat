package com.groentved.andreas.chordrecognition.ui.base

import android.arch.lifecycle.AndroidViewModel
import com.groentved.andreas.chordrecognition.ChordApp
import com.groentved.andreas.chordrecognition.domain.Domain
import com.groentved.andreas.chordrecognition.ui.inject.component.DaggerActivityComponent
import com.groentved.andreas.chordrecognition.ui.inject.module.ActivityModule
import javax.inject.Inject

/**
 * Created by Andreas Grøntved on 05-08-2017.
 **/

abstract class BaseViewModel(application: ChordApp) : AndroidViewModel(application) {

    @Inject
    lateinit var domain: Domain

    init {
        DaggerActivityComponent.builder().applicationComponent(application.component).activityModule(ActivityModule()).build().inject(this)
    }

}