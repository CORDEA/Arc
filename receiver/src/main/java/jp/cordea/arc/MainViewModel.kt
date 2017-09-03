package jp.cordea.arc

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import jp.cordea.base.LightRepository

/**
 * Created by cordea on 2017/09/03.
 */
class MainViewModel : ViewModel() {

    private val repository = LightRepository()

    fun isLightOn(): LiveData<Boolean> =
            LiveDataReactiveStreams
                    .fromPublisher<Boolean>(repository.value)

}