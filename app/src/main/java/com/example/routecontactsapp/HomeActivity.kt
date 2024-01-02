package com.example.routecontactsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HomeActivity : AppCompatActivity() {
    //Dialog Views
    lateinit var nameInputLayout: TextInputLayout
    lateinit var nameEd: TextInputEditText
    lateinit var phoneInputLayout: TextInputLayout
    lateinit var phoneEd: TextInputEditText
    lateinit var descriptionEd: TextInputEditText
    lateinit var addBtn: Button
    lateinit var dialog: AlertDialog

    //Home Views
    lateinit var addContactBtn: FloatingActionButton
    val contacts = ArrayList<Contact>()
    lateinit var contact: Contact
    lateinit var contactsAdapter: ContactsAdapter
    lateinit var contactsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initContactDialog()
        contactsAdapter = ContactsAdapter(contacts)
        contactsAdapter.onContactClick = object : ContactsAdapter.OnContactClick{
            override fun onClick(contact: Contact, position:Int) {
                val intent = Intent(this@HomeActivity, ContactDetailsActivity::class.java)
                intent.putExtra("contact", contacts[position])
                startActivity(intent)
            }

        }

        contactsRecyclerView = findViewById(R.id.contactsRecyclerView)
        contactsRecyclerView.adapter = contactsAdapter
        addContactBtn = findViewById(R.id.addContactBtn)
        addContactBtn.setOnClickListener {
            dialog.show()
            fieldsTextWatchers()
            addBtn.setOnClickListener {
                if (nameInputLayout.helperText.isNullOrEmpty() && phoneInputLayout.helperText.isNullOrEmpty()){
                    contact = Contact(nameEd.text.toString(), phoneEd.text.toString(), descriptionEd.text.toString())
                    contacts.add(contact)
                    dialog.dismiss()
                    resetDialog()
                    contactsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun resetDialog() {
        nameEd.text?.clear()
        phoneEd.text?.clear()
        nameInputLayout.helperText = Constants.REQUIRED
        phoneInputLayout.helperText = Constants.REQUIRED
        descriptionEd.text?.clear()
    }

    private fun initContactDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Contact Details")
        val contactView = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(contactView)
        dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        initDialogViews(contactView)
    }

    private fun initDialogViews(contactView: View) {
        nameInputLayout = contactView.findViewById(R.id.nameInputLayout)
        nameEd = contactView.findViewById(R.id.nameEd)
        phoneInputLayout = contactView.findViewById(R.id.phoneInputLayout)
        phoneEd = contactView.findViewById(R.id.phoneEd)
        descriptionEd = contactView.findViewById(R.id.descriptionEd)
        addBtn = contactView.findViewById(R.id.addBtn)
    }

    private fun fieldsTextWatchers(){
        nameEd.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameInputLayout.helperText = Constants.REQUIRED
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameInputLayout.helperText = Constants.NAME_HELPER
            }

            override fun afterTextChanged(p0: Editable?) {
                if (nameEd.text.toString().length >= 3)
                    nameInputLayout.helperText = ""
            }

        })

        phoneEd.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                phoneInputLayout.helperText = Constants.REQUIRED
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                phoneInputLayout.helperText = Constants.PHONE_HELPER
            }

            override fun afterTextChanged(p0: Editable?) {
                if (phoneEd.text.toString().length == 11)
                    phoneInputLayout.helperText = ""
            }

        })
    }

}
