class ListContactsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter
    private val contactsList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contacts)

        recyclerView = findViewById(R.id.recyclerViewContacts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Carregar contatos do SharedPreferences
        loadContacts()

        // Configurar o adapter do RecyclerView
        adapter = ContactAdapter(contactsList)
        recyclerView.adapter = adapter
    }

    private fun loadContacts() {
        val sharedPreferences = getSharedPreferences("contacts", MODE_PRIVATE)
        val allEntries = sharedPreferences.all

        // Iterar sobre os contatos salvos no SharedPreferences
        for ((key, value) in allEntries) {
            if (key.endsWith("_nome")) {
                val id = key.replace("_nome", "")
                val nome = value as String
                val telefone = sharedPreferences.getString("$id_telefone", "") ?: ""
                val email = sharedPreferences.getString("$id_email", "") ?: ""
                val endereco = sharedPreferences.getString("$id_endereco", "") ?: ""

                // Criar um objeto de Contato e adicionar à lista
                val contato = Contact(nome, telefone, email, endereco)
                contactsList.add(contato)
            }
        }
        // Atualizar o RecyclerView com os novos dados
        adapter.notifyDataSetChanged()
    }
}
