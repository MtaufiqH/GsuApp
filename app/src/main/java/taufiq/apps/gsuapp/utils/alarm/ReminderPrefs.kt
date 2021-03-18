package taufiq.apps.gsuapp.utils.alarm

import android.content.Context
import androidx.core.content.edit

/**
 * Created By Taufiq on 3/18/2021.
 *
 */
class ReminderPrefs(context: Context) {
    companion object {
        const val PREFS_NAME = "reminder_preference"
        const val REMIND_KEY = "reminded"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: Reminders) {
        prefs.edit {
            putBoolean(REMIND_KEY, value.isReminded)
            apply()
        }
    }

    fun getReminder(): Reminders {
        val model = Reminders()
        model.isReminded = prefs.getBoolean(REMIND_KEY, false)
        return model
    }
}