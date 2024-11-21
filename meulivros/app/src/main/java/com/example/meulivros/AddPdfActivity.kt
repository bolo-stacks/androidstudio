import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_pdf.*

class AddPdfActivity : AppCompatActivity() {
    private val PICK_PDF_REQUEST = 1
    private var selectedLibrary: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pdf)

        val sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)
        val libraries = sharedPreferences.getStringSet("libraries", mutableSetOf())?.toList() ?: listOf()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, libraries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLibraries.adapter = adapter

        spinnerLibraries.setOnItemSelectedListener { _, _, position, _ ->
            selectedLibrary = libraries[position]
        }

        btnSelectPdf.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(intent, PICK_PDF_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            val uri: Uri? = data?.data
            uri?.let {
                val sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val libraryPdfs = sharedPreferences.getStringSet(selectedLibrary, mutableSetOf()) ?: mutableSetOf()
                libraryPdfs.add(it.toString())
                editor.putStringSet(selectedLibrary, libraryPdfs)
                editor.apply()
            }
        }
    }
}
