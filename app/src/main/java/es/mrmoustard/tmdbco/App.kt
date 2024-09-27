package es.mrmoustard.tmdbco

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import es.mrmoustard.data.source.local.database.MoviesDatabase

@HiltAndroidApp
class App : Application() {

    lateinit var db: MoviesDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = MoviesDatabase.getInstance(context = this)
    }
}