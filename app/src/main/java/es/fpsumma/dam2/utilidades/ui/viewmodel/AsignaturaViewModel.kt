package es.fpsumma.dam2.utilidades.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import es.fpsumma.dam2.utilidades.data.local.AppDatabase
import es.fpsumma.dam2.utilidades.model.Asignatura
import es.fpsumma.dam2.utilidades.model.Tarea
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AsignaturaViewModel(app: Application) : AndroidViewModel(app) {

    private val db = Room.databaseBuilder(
        app, AppDatabase::class.java, "asignaturas.db"
    ).fallbackToDestructiveMigration(false).build()


    private val dao = db.tareaDao()


    val asignaturas: StateFlow<List<Asignatura>> =
        dao.getAllAsignaturas().stateIn(
            viewModelScope,             
            SharingStarted.Lazily,
            emptyList()
        )


    fun addAsignatura(asignatura: String, trimestre: Int, curso: Int) = viewModelScope.launch {
        dao.insert(Asignaturas(asignatura = asignatura, trimestre = trimestre, curso = curso))
    }


    fun deleteAsignatura(asignatura: Asignatura) = viewModelScope.launch {
        dao.delete(asignatura)
    }

}