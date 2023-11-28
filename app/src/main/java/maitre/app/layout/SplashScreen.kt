package maitre.app.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import maitre.app.R
import maitre.app.utils.Sessao

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Sessao.urlApi = getString(R.string.url_api)

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, Home::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}