package jp.cordea.base

import io.reactivex.Flowable

/**
 * Created by cordea on 2017/09/03.
 */
class LightRepository : Repository<Boolean>(false, "light") {

    fun on() {
        ref.setValue(true)
    }

    fun off() {
        ref.setValue(false)
    }

    fun switch() {
        if (currentValue) {
            off()
            return
        }
        on()
    }

    val value: Flowable<Boolean> = getValueChangedFlowable()
}