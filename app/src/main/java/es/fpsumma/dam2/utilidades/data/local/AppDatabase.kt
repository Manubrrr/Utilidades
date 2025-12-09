package es.fpsumma.dam2.utilidades.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.fpsumma.dam2.utilidades.model.Asignatura
import es.fpsumma.dam2.utilidades.model.Tarea

@Database(
    entities = [Tarea::class, Asignatura::class], // CORRECCIÓN: Usar la clase Asignatura
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDao
    abstract fun asignaturasDao(): AsignaturasDao // CORRECCIÓN: Nombre consistente

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "utilidades"
                ).fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}