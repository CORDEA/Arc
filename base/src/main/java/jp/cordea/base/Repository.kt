package jp.cordea.base

import com.google.firebase.database.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by cordea on 2017/09/03.
 */
open class Repository<T: Any>(private val default: T, name: String) {

    protected var currentValue: T = default
        private set

    protected val ref: DatabaseReference

    init {
        val database = FirebaseDatabase.getInstance()
        ref = database.getReference("arc/" + name)
    }

    protected fun getValueChangedFlowable(): Flowable<T> =
            Flowable
                    .create({ emitter ->
                        ref.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError?) {
                                p0?.toException()?.let {
                                    emitter.onError(it)
                                }
                            }

                            override fun onDataChange(p0: DataSnapshot?) {
                                p0?.apply {
                                    getValue(default::class.java)?.let {
                                        currentValue = it
                                        emitter.onNext(it)
                                    }
                                }
                            }
                        })
                    }, BackpressureStrategy.LATEST)

    fun clear() {
        ref.removeValue()
    }
}
