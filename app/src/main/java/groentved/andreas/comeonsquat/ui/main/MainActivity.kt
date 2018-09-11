package groentved.andreas.comeonsquat.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import groentved.andreas.comeonsquat.R
import groentved.andreas.comeonsquat.ui.base.BaseActivity
import groentved.andreas.comeonsquat.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel

    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        button_start.setOnClickListener {
            viewModel.toggleListening()
        }


    }

    fun setUpListeners() {
        viewModel.getAngleUpdates().observe(this, Observer {
            text_start_info.text = "Your squat is ${it}% awesome"
        })

        viewModel.getParallelUpdates().observe(this, Observer {
            viewModel.playSound()
        })
    }

    fun firstTime() {
        if (viewModel.isFirstTime()) startActivity(Intent(this, SettingsActivity::class.java))
    }


    override fun setUpToolBar() = setSupportActionBar(toolbar)

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_help -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
