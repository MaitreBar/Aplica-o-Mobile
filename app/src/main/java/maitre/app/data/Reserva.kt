package maitre.app.data

import java.io.Serializable

data class Reserva(
    val id: String?,
    val dtReserva: String,
    val horaReserva: String,
    val checkIn: Boolean,
    val dtHoraCheckIn: String?,
    val checkout: Boolean,
    val dtHoraCheckOut: String?,
    var feedback: String?,
    val estabelecimento: Estabelecimento,
    val usuario: Usuario,
    val assentos: List<Assento>
) : Serializable
