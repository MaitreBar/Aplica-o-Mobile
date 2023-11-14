package maitre.app.data

data class Reserva(
    val dtReserva: String,
    val horaReserva: String,
    val checkin: Boolean,
    val dtHoraCheckIn: String,
    val checkout: Boolean,
    val dtHoraCheckOut: String,
    val feedback: String,
    val estabelecimento: Estabelecimento,
    val usuario: Usuario,
    val assentos: List<Assento>
)
