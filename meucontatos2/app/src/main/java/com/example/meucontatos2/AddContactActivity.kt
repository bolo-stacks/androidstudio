class AddContactActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextTelefone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextEndereco: EditText
    private lateinit var buttonSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        // Inicializando os campos de entrada
        editTextNome = findViewById(R.id.editTextNome)
        editTextTelefone = findViewById(R.id.editTextTelefone)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextEndereco = findViewById(R.id.editTextEndereco)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        // Configurando o clique do botão para salvar o contato
        buttonSalvar.setOnClickListener {
            salvarContato()
        }
    }

    private fun salvarContato() {
        val nome = editTextNome.text.toString()
        val telefone = editTextTelefone.text.toString()
        val email = editTextEmail.text.toString()
        val endereco = editTextEndereco.text.toString()

        if (nome.isNotEmpty() && telefone.isNotEmpty()) {
            // Salvar contato no SharedPreferences
            val sharedPreferences = getSharedPreferences("contacts", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            // Usando um identificador único para salvar múltiplos contatos
            val contatoId = System.currentTimeMillis().toString() // Usando timestamp como ID único

            // Salvar os dados no SharedPreferences
            editor.putString("$contatoId_nome", nome)
            editor.putString("$contatoId_telefone", telefone)
            editor.putString("$contatoId_email", email)
            editor.putString("$contatoId_endereco", endereco)
            editor.apply()

            // Redirecionar para a tela de listagem
            Toast.makeText(this, "Contato salvo com sucesso!", Toast.LENGTH_SHORT).show()
            finish() // Voltar para a tela anterior
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show()
        }
    }
}
