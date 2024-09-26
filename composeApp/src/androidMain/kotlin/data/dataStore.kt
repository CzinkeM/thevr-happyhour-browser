package data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDateStore(context: Context): DataStore<Preferences> {
    return createDateStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}