package maitre.app.data

data class Usuario(
    val nome: String,
    val email: String,
    val cpf: String,
    val dtNasc: String,
    val celular: String,
    val rg: String,
    val senha: String,
    val reservas: List<Reserva>?
)
