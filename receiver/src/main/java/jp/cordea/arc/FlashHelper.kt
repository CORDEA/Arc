package jp.cordea.arc

import android.content.Context
import android.hardware.Camera

/**
 * Created by cordea on 2017/09/03.
 */
class FlashHelper {

    private var camera: Camera? = null

    fun on() {
        if (camera != null) {
            return
        }

        // TODO: Camera2
        Camera.open().apply {
            parameters = parameters.apply {
                flashMode = Camera.Parameters.FLASH_MODE_TORCH
            }
            startPreview()
            camera = this
        }
    }

    fun off() {
        camera?.apply {
            parameters = parameters.apply {
                flashMode = Camera.Parameters.FLASH_MODE_OFF
            }
            stopPreview()
            release()
        }
        camera = null
    }
}