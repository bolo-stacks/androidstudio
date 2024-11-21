import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_library.*

class ViewLibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_library)

        val sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)
        val libraries = sharedPreferences.getStringSet("libraries", mutableSetOf())?.toList() ?: listOf()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, libraries)
        listViewLibraries.adapter = adapter

        listViewLibraries.setOnItemClickListener { _, _, position, _ ->
            val libraryName = libraries[position]
            val pdfs = sharedPreferences.getStringSet(libraryName, mutableSetOf())?.toList() ?: listOf()
            // Pode adicionar lógica para abrir PDFs aqui.
        }
    }
}
