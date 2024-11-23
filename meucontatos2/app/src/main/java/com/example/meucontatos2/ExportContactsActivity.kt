class ExportContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_contacts)

        val sharedPrefs = getSharedPreferences("Contacts", MODE_PRIVATE)
        val allContacts = sharedPrefs.all

        val exportToWhatsApp = findViewById<TextView>(R.id.exportWhatsApp)
        val exportToEmail = findViewById<TextView>(R.id.exportEmail)

        exportToWhatsApp.setOnClickListener {
            val contactsText = allContacts.values.joinToString("\n")
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, contactsText)
            intent.putExtra(Intent.EXTRA_TITLE, "Exportar Contatos")
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        }

        exportToEmail.setOnClickListener {
            val contactsText = allContacts.values.joinToString("\n")
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contatos")
            intent.putExtra(Intent.EXTRA_TEXT, contactsText)
            startActivity(intent)
        }
    }
}
