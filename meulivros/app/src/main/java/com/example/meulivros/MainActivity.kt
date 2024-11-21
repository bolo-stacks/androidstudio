import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurações de clique para os TextViews
        tvAddLibrary.setOnClickListener {
            startActivity(Intent(this, AddLibraryActivity::class.java))
        }

        tvAddPdf.setOnClickListener {
            startActivity(Intent(this, AddPdfActivity::class.java))
        }

        tvViewLibraries.setOnClickListener {
            startActivity(Intent(this, ViewLibraryActivity::class.java))
        }
    }
}

