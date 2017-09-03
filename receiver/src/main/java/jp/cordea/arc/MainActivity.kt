package jp.cordea.arc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val intent = Intent(this, ArcService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        val intent = Intent(this, ArcService::class.java)
        stopService(intent)

        super.onDestroy()
    }
}
