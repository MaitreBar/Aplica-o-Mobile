package maitre.app.utils

import androidx.lifecycle.ViewModel
import maitre.app.data.Reserva

class SharedViewModel : ViewModel() {
    lateinit var dia : String
    lateinit var hora : String
    lateinit var novaReserva: Reserva
}