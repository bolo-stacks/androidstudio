class AddEditContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_contact)

        // Recuperar campos do layout
        val nameField = findViewById<EditText>(R.id.nameField)
        val phoneField = findViewById<EditText>(R.id.phoneField)
        val emailField = findViewById<EditText>(R.id.emailField)
        val addressField = findViewById<EditText>(R.id.addressField)

        // Salvar contato
        findViewById<TextView>(R.id.saveContact).setOnClickListener {
            val contact = "Name: ${nameField.text}, Phone: ${phoneField.text}, Email: ${emailField.text}, Address: ${addressField.text}"
            val sharedPrefs = getSharedPreferences("Contacts", MODE_PRIVATE)
            sharedPrefs.edit().putString(nameField.text.toString(), contact).apply()
            Toast.makeText(this, "Contato salvo!", Toast.LENGTH_SHORT).show()
        }
    }
}
