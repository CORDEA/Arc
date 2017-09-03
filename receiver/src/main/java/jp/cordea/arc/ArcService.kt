package jp.cordea.arc

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import io.reactivex.disposables.Disposable
import jp.cordea.base.LightRepository

/**
 * Created by cordea on 2017/09/03.
 */
class ArcService : Service() {

    private val repository = LightRepository()

    private val helper = FlashHelper()

    private var disposable: Disposable? = null

    override fun onBind(p0: Intent?): IBinder = Binder()

    override fun onCreate() {
        super.onCreate()

        disposable = repository.value.subscribe {
            if (it) {
                helper.on()
                return@subscribe
            }
            helper.off()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pendingIntent = PendingIntent
                .getActivity(this, 0,
                        Intent(this, MainActivity::class.java), 0)
        val notification =
                NotificationCompat
                        .Builder(this, ChannelId)
                        .setContentIntent(pendingIntent)
                        .build()

        startForeground(startId, notification)

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    companion object {
        private val ChannelId = "1"

    }
}