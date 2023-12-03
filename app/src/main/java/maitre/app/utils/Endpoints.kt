package maitre.app.utils

import maitre.app.data.Assento
import maitre.app.data.Estabelecimento
import maitre.app.data.Reserva
import maitre.app.data.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Endpoints {

    @GET("/usuarios/{email}/{senha}")
    fun getLogin(@Path("email") email: String, @Path("senha") senha: String) : Call<Usuario>

    @POST("/usuarios")
    fun cadastrar(@Body usuario: Usuario) : Call<Usuario>

    @PUT("/usuarios/{id}")
    fun atualizar(@Body usuario: Usuario, @Path("id") id: String) : Call<Usuario>

    @GET("/estabelecimentos")
    fun getEstabelecimentos() : Call<List<Estabelecimento>>

    @GET("/reservas/busca-por-usuario/{id}")
    fun getReservas(@Path("id") id: String) : Call<List<Estabelecimento>>

    @GET("/reservas/busca-por-estabelecimento/{id}")
    fun getReservasEstabelecimentos(@Path("id") id: Int) : Call<List<Usuario>>

    @POST("/reservas")
    fun criarReserva(@Body reserva: Reserva) : Call<Reserva>

    @DELETE("/reservas/{id}")
    fun deleteReserva(@Path("id") id: String) : Call<Reserva>

    @GET("/assentos/{id}")
    fun getAssentoById(@Path("id") id : Int) : Call<Assento>
}