package maitre.app.data

data class Assento(
    val id: String,
    val disponivel: Boolean,
    val reserva: Reserva,
    val estabelecimento: Estabelecimento
)