package maitre.app.data

data class Assento(
    val idAssento: Integer,
    val disponivel: Boolean,
    val reserva: Reserva,
    val estabelecimento: Estabelecimento
)