package groentved.andreas.comeonsquat.ui.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
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
    }


    private fun calibrateButtons() {
        calibrate_parallel.setOnClickListener {
            viewModel.stop()
            if (isCalibrating) return@setOnClickListener
            isCalibrating = true
            viewModel.update()
            startPar = false
            calibrate_parallel.setBackgroundResource(R.drawable.ic_stop_black_24dp)
            viewModel.progressTimer().observe(this, Observer {
                if (startPar) return@Observer
                calibrate_parallel.text = viewModel.formatTimeLeft(it!!)
            })
            viewModel.endTimer().observe(this, Observer {
                if (startPar) return@Observer
                calibrate_parallel.text = "Parallel"
                calibrate_parallel.setBackgroundResource(R.drawable.start)
                isCalibrating = false
            })
        }

        calibrate_start.setOnClickListener {
            viewModel.stop()
            if (isCalibrating) return@setOnClickListener
            isCalibrating = true
            viewModel.update()
            startPar = true
            calibrate_start.setBackgroundResource(R.drawable.ic_stop_black_24dp)
            viewModel.progressTimer().observe(this, Observer {
                if (!startPar) return@Observer
                calibrate_start.text = viewModel.formatTimeLeft(it!!)
            })
            viewModel.endTimer().observe(this, Observer {
                if (!startPar) return@Observer
                calibrate_start.text = "Start"
                calibrate_start.setBackgroundResource(R.drawable.start)
                isCalibrating = false
            })
        }
    }

    private fun placement() {
        placement_group.check(viewModel.getPosition())
        placement_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == radio_top_of_quad.id) {
                viewModel.setPosition(PhonePosition.BACK_PARALLEL_TO_TOP_OF_QUAD.phoneVal)
            } else if (checkedId == radio_side_of_quad.id) {
                viewModel.setPosition(PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD.phoneVal)
            }
        }
    }

    private fun error() {
        errorSeekBar.progress = viewModel.getError().toInt()
        error_percentage.text = "Error of ${viewModel.getError().toInt()}%"
        errorSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) viewModel.setError(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        });
    }

    private fun volume() {
        volumeSeekBar.progress = viewModel.getVolume()
        volume_percentage.text = "Volume: ${viewModel.getError().toInt()}%"
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) viewModel.setVolume(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        });
    }

}
