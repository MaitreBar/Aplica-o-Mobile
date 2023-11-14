package maitre.app.data

data class Estabelecimento(
    val idEstabelecimento: Integer,
    val nome: String,
    val senha: String,
    val logradouro: String,
    val numero: String,
    val complemento: String,
    val cep: String,
    val diasDaSemana: String,
    val faixaDePreco: String,
    val cnpj: String,
    val telefoneContato: String,
    val horarioAbertura: String,
    val horarioFechamento: String,
    val descricao: String,
    val email: String,
    val assentos: List<Assento>,
    val tags: String,
    val reservas: List<Reserva>
)