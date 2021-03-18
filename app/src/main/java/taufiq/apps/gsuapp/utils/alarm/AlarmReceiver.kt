package taufiq.apps.gsuapp.utils.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import taufiq.apps.gsuapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showAlarmNotification(context)
    }

    private fun showAlarmNotification(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage("taufiq.apps.gsuapp")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notifications =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_reminder)
            .setContentIntent(pendingIntent)
            .setContentTitle(context.getString(R.string.github_search))
            .setContentText(context.getString(R.string.alarm_message))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableVibration(true)
                vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            }
            builder.setChannelId(CHANNEL_ID)
            notifications.createNotificationChannel(channel)
        }
        val notification = builder?.build()
        notifications.notify(NOTIFICATION_ID, notification)
    }

    fun setRepeatingAlarm(context: Context, type: String, time: String, message: String) {
        if (isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context, context.getString(R.string.repeating_on), Toast.LENGTH_SHORT).show()

    }

    fun cancelAlarm(context: Context) {
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val myIntent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEATING
        val pendingIntents = PendingIntent.getBroadcast(context, requestCode, myIntent, 0)
        pendingIntents.cancel()
        alarm.cancel(pendingIntents)
        Toast.makeText(context, context.getString(R.string.repeating_off), Toast.LENGTH_SHORT)
            .show()

    }

    private fun isDateInvalid(date: String, format: String): Boolean {
        return try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.isLenient = false
            df.parse(date)
            false
        } catch (e: ParseException) {
            true
        }
    }


    companion object {
        const val CHANNEL_NAME = "Alarm channel"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"
        const val CHANNEL_ID = "Channel_1"
        const val NOTIFICATION_ID = 1
        const val ID_REPEATING = 101
        const val TIME_FORMAT = "HH:mm"

    }
}