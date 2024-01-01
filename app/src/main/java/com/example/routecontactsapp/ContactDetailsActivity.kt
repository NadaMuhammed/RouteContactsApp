package com.example.routecontactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var contactDetailsNameTv: TextView
    lateinit var contactDetailsPhoneTv: TextView
    lateinit var contactDetailsDescriptionTv: TextView
    lateinit var descriptionCardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        contactDetailsNameTv = findViewById(R.id.contactDetailsNameTv)
        contactDetailsPhoneTv = findViewById(R.id.contactDetailsPhoneTv)
        contactDetailsDescriptionTv = findViewById(R.id.contactDetailsDescriptionTv)
        descriptionCardView = findViewById(R.id.descriptionCardView)
        val contact = intent.getParcelableExtra<Contact>("contact")
        contactDetailsNameTv.text = contact?.name
        contactDetailsPhoneTv.text = contact?.phone
        if (contact?.description.isNullOrEmpty())
            descriptionCardView.visibility = View.INVISIBLE
        else
            contactDetailsDescriptionTv.text = contact?.description
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}