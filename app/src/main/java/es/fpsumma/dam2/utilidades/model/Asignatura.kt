package es.fpsumma.dam2.utilidades.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asignaturas")
data class Asignatura(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "modulo")
    val modulo: String,

    @ColumnInfo(name = "trimestre")
    val trimestre: Int,

    @ColumnInfo(name = "curso")
    val curso: Int,


)