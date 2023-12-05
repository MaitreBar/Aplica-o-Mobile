package maitre.app.utils

import maitre.app.data.Estabelecimento
import maitre.app.data.Reserva
import maitre.app.data.Usuario

object Sessao {
    lateinit var urlApi: String
    var usuario: Usuario? = null
    var estabelecimento: Estabelecimento? = null
    var reserva: Reserva? = null
}