package jp.cordea.arc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.cordea.base.LightRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val repository = LightRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            repository.switch()
        }
    }
}
