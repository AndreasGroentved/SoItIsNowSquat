package groentved.andreas.comeonsquat.ui.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import groentved.andreas.comeonsquat.R
import groentved.andreas.comeonsquat.domain.PhonePosition
import groentved.andreas.comeonsquat.ui.base.BaseActivity
import kotlinx.android.synthetic.main.content_settings.*


class SettingsActivity : BaseActivity() {
    private lateinit var viewModel: SettingsViewModel
    private var startPar = false
    private var isCalibrating = false

    override fun getLayout(): Int = R.layout.activity_settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        placement()
        error()
        volume()
        calibrateButtons()
        setTimerListeners()
    }


    private fun calibrateButtons() {
        calibrate_parallel.setOnClickListener {
            viewModel.stop()
            if (isCalibrating) return@setOnClickListener
            isCalibrating = true
            viewModel.update()
            startPar = false
            calibrate_parallel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_stop)
        }

        calibrate_start.setOnClickListener {
            viewModel.stop()
            if (isCalibrating) return@setOnClickListener
            isCalibrating = true
            viewModel.update()
            startPar = true
            calibrate_start.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_stop)
        }
    }

    private fun setTimerListeners() {
        viewModel.progressTimer().observe(this, Observer {
            if (!startPar)
                calibrate_parallel.text = viewModel.formatTimeLeft(it!!)
            else
                calibrate_start.text = viewModel.formatTimeLeft(it!!)
        })
        viewModel.endTimer().observe(this, Observer {
            if (!startPar) {
                calibrate_parallel.text = "Parallel"
                calibrate_parallel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icon_start)
            } else {
                calibrate_start.text = "Start"
                calibrate_start.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icon_start)
            }
            isCalibrating = false
            viewModel.stop()
            viewModel.updateStartParallelAcceleration(startPar)
        })

    }

    private fun placement() {
        getChildAt(viewModel.getPosition())?.isChecked = true
        placement_group.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == radio_top_of_quad.id) {
                viewModel.setPosition(PhonePosition.BACK_PARALLEL_TO_TOP_OF_QUAD.phoneVal)
            } else if (checkedId == radio_side_of_quad.id) {
                viewModel.setPosition(PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD.phoneVal)
            }
        }
    }

    private fun getChildAt(position: Int): RadioButton? {
        var index = 0
        for (i in 0 until placement_group.childCount) {
            val radioButton = placement_group.getChildAt(i)
            if (radioButton is RadioButton) {
                if (index == position) return radioButton
                index++
            }
        }
        return null
    }

    private fun error() {
        errorSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser)
                    viewModel.setError(progress.toFloat())
                error_percentage.text = "Error of ${viewModel.getError().toInt()}%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
        })
        errorSeekBar.progress = viewModel.getError().toInt()
    }

    private fun volume() {
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser)
                    viewModel.setVolume(progress.toFloat())

                volume_percentage.text = "Volume: ${viewModel.getVolume()}%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
        })
        volumeSeekBar.progress = viewModel.getVolume()

    }

}
