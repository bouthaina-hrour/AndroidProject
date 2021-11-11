package com.faircorp
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.service.WindowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val windowService =WindowService()
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        var windowName : String
        var windowStatus : String
        var roomName : String
        var currentTemperature : String?

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().windowsApiService.findById(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        val window = it.body()
                        if (window != null) {
                            windowName = window.name
                            roomName = window.roomName
                            windowStatus = window.windowStatus.toString()
                            findViewById<TextView>(R.id.window_name).text = windowName
                            findViewById<TextView>(R.id.room_name).text = roomName
                            findViewById<TextView>(R.id.window_status).text = windowStatus

                        }

                    }

                }


                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on window loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }


    }

}
}