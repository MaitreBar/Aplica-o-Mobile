package maitre.app.utils

import maitre.app.data.Usuario
import retrofit2.Call
import retrofit2.http.Body
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
}