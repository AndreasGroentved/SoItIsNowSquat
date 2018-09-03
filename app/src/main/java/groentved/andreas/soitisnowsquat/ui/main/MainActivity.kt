package groentved.andreas.soitisnowsquat.ui.main

import android.os.Bundle
import groentved.andreas.soitisnowsquat.R
import groentved.andreas.soitisnowsquat.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun setUpToolBar() = setSupportActionBar(toolbar)

}
