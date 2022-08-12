package rakib.hasan.multilingualconfig

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rakib.hasan.multilingualconfig.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lang: String = "en"

    override fun onResume() {
        super.onResume()
        binding.toggleButton.isChecked = Utils.isChecked
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toggleButton.isChecked = Utils.isChecked
        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            Utils.isChecked = isChecked
            if (isChecked){
                setAppLocale("bn")
            }else {
                setAppLocale("en")
            }
        }

    }

    fun setAppLocale(languageToLoad: String){
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        this.resources.updateConfiguration(config, this.resources.displayMetrics)
        refresh()
    }

    private fun refresh() {
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(0, 0)
    }

}