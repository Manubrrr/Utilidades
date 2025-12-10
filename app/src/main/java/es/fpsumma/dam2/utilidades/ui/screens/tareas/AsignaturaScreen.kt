package es.fpsumma.dam2.utilidades.ui.screens.tareas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignaturaScreen(
    navController: NavController,
    vm: AsignaturaViewModel,
    modifier: Modifier = Modifier
) {

    val asignaturas by vm.asignaturas.collectAsState()

    var modulo by rememberSaveable { mutableStateOf("") }
    var trimestre by rememberSaveable { mutableStateOf("") }
    var curso by rememberSaveable { mutableStateOf("") }


    fun handleAddAsignatura() {
        val trimestreNum = trimestre.toIntOrNull() ?: 1
        val cursoNum = curso.toIntOrNull() ?: 1
        vm.addAsignatura(modulo, trimestreNum, cursoNum)
        modulo = ""
        trimestre = ""
        curso = ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de Asignaturas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
                    OutlinedTextField(
                        value = modulo,
                        onValueChange = { modulo = it },
                        label = { Text("Módulo") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = trimestre,
                        onValueChange = { trimestre = it },
                        label = { Text("Trimestre") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = curso,
                        onValueChange = { curso = it },
                        label = { Text("Curso") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { handleAddAsignatura() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Añadir asignatura")
                    }
            Spacer(modifier = Modifier.height(15.dp))

            if (asignaturas.isEmpty()) {
                Text(
                    "Todavía no hay asignaturas creadas",
                )
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    items(asignaturas, key = { it.id }) { asignatura ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        asignatura.modulo,
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        "Trimestre: ${asignatura.trimestre} · Curso: ${asignatura.curso}"
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                IconButton(
                                    onClick = { vm.deleteAsignatura(asignatura) },
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Borrar asignatura"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}