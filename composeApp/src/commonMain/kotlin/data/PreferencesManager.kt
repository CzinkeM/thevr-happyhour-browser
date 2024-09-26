package data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesManager(
    private val dataStore: DataStore<Preferences>
) {

    private object Keys {
        val shouldShowDisclaimerDialogKey = booleanPreferencesKey("SHOULD_SHOW_DISCLAIMER_DIALOG")
    }

    suspend fun changeShouldShowDisclaimerDialog(
        shouldShow: Boolean
    ) {
        dataStore.edit { dataStore ->
            dataStore[Keys.shouldShowDisclaimerDialogKey] = shouldShow
        }
    }

    fun shouldShowDisclaimerDialog(): Flow<Boolean> {
        return dataStore.data.map {
            it[Keys.shouldShowDisclaimerDialogKey] ?: true
        }
    }
}