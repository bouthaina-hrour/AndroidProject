package com.faircorp
import android.os.Bundle
import android.widget.TextView
import com.faircorp.service.WindowService

class WindowActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val windowService =WindowService()
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        val window = windowService.findById(id)
        if (window != null) {
            findViewById<TextView>(R.id.window_name).text = window.name
            findViewById<TextView>(R.id.room_name).text = window.room.name
            findViewById<TextView>(R.id.window_current_temperature).text = window.room.currentTemperature?.toString()
            findViewById<TextView>(R.id.window_target_temperature).text = window.room.targetTemperature?.toString()
            findViewById<TextView>(R.id.window_status).text = window.status.toString()
        }

    }
}