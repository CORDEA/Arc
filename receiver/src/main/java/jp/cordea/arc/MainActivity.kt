package jp.cordea.arc

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val lifecycle = LifecycleRegistry(this)

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val intent = Intent(this, ArcService::class.java)
        startService(intent)

        subscribe()
    }

    private fun subscribe() {
        viewModel
                .isLightOn()
                .observe(this, Observer {
                    it?.let {
                        lightSwitch.isChecked = it
                    }
                })
    }

    override fun onDestroy() {
        val intent = Intent(this, ArcService::class.java)
        stopService(intent)

        super.onDestroy()
    }

    override fun getLifecycle(): LifecycleRegistry = lifecycle
}
