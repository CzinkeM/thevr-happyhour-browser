package domain

import android.content.Context
import android.content.Intent
import android.net.Uri

actual class AppLauncher(
    private val context: Context
) {
    actual fun launchApp(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}