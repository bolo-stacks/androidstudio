class ExportContactsActivity : AppCompatActivity() {

    private lateinit var textViewExportWhatsApp: TextView
    private lateinit var textViewExportEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_contacts)

        textViewExportWhatsApp = findViewById(R.id.textViewExportWhatsApp)
        textViewExportEmail = findViewById(R.id.textViewExportEmail)

        // Exportar para WhatsApp
        textViewExportWhatsApp.setOnClickListener {
            val contactsList = loadContactsFromSharedPreferences()
            val message = buildContactsMessage(contactsList)

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
                setPackage("com.whatsapp") // Para WhatsApp
            }
            startActivity(sendIntent)
        }

        // Exportar para E-mail
        textViewExportEmail.setOnClickListener {
            val contactsList = loadContactsFromSharedPreferences()
            val message = buildContactsMessage(contactsList)

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "message/rfc822"
            }
            startActivity(Intent.createChooser(sendIntent, "Enviar por E-mail"))
        }
    }

    // Função para construir a mensagem dos contatos
    private fun buildContactsMessage(contactsList: List<Contact>): String {
        val builder = StringBuilder()
        for (contact in contactsList) {
            builder.append("Nome: ${contact.nome}\n")
            builder.append("Telefone: ${contact.telefone}\n")
            builder.append("E-mail: ${contact.email}\n")
            builder.append("Endereço: ${contact.endereco}\n\n")
        }
        return builder.toString()
    }

    // Função para carregar contatos do SharedPreferences
    private fun loadContactsFromSharedPreferences(): List<Contact> {
        val sharedPreferences = getSharedPreferences("contacts", MODE_PRIVATE)
        val allEntries = sharedPreferences.all
        val contactsList = mutableListOf<Contact>()

        for ((key, value) in allEntries) {
            if (key.endsWith("_nome")) {
                val id = key.replace("_nome", "")
                val nome = value as String
                val telefone = sharedPreferences.getString("$id_telefone", "") ?: ""
                val email = sharedPreferences.getString("$id_email", "") ?: ""
                val endereco = sharedPreferences.getString("$id_endereco", "") ?: ""

                val contact = Contact(nome, telefone, email, endereco)
                contactsList.add(contact)
            }
        }
        return contactsList
    }
}
