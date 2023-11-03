package maitre.app.utils

import maitre.app.data.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Optional

interface Endpoints {

    @GET("/usuarios/{email}/{senha}")
    fun getLogin(@Path("email") email: String, @Path("senha") senha: String) : Call<Optional<Usuario>>

    @POST("/usuarios")
    fun cadastrar(@Body usuario: Usuario) : Call<Usuario>

}