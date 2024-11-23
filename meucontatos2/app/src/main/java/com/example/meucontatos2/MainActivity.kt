class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Abrir tela de listar contatos
        findViewById<TextView>(R.id.listContacts).setOnClickListener {
            startActivity(Intent(this, ListContactsActivity::class.java))
        }

        // Abrir tela de adicionar contato
        findViewById<TextView>(R.id.addContact).setOnClickListener {
            startActivity(Intent(this, AddEditContactActivity::class.java))
        }

        // Abrir tela de editar contato (se necessário)
        findViewById<TextView>(R.id.editContact).setOnClickListener {
            startActivity(Intent(this, EditContactActivity::class.java))
        }

        // Apagar todos os contatos
        findViewById<TextView>(R.id.clearContacts).setOnClickListener {
            clearAllContacts()
        }

        // Exportar contatos
        findViewById<TextView>(R.id.exportContacts).setOnClickListener {
            startActivity(Intent(this, ExportContactsActivity::class.java))
        }
    }

    // Função para apagar todos os contatos
    private fun clearAllContacts() {
        val sharedPrefs = getSharedPreferences("Contacts", MODE_PRIVATE)
        sharedPrefs.edit().clear().apply()
    }
}
