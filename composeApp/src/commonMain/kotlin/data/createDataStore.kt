package data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

fun createDateStore(producePathString: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath (
        produceFile = {producePathString().toPath()}
    )
}

internal const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"