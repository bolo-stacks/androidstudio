import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_library.*

class AddLibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_library)

        btnSaveLibrary.setOnClickListener {
            val libraryName = etLibraryName.text.toString()
            if (libraryName.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val libraries = sharedPreferences.getStringSet("libraries", mutableSetOf()) ?: mutableSetOf()
                libraries.add(libraryName)
                editor.putStringSet("libraries", libraries)
                editor.apply()

                Toast.makeText(this, "Biblioteca salva!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Digite um nome válido!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
