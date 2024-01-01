package com.example.routecontactsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ContactsAdapter(var contacts: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    lateinit var onContactClick: OnContactClick

    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.contactNameTv)
        var phone = itemView.findViewById<TextView>(R.id.contactPhoneTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.name.text = contacts[position].name
        holder.phone.text = contacts[position].phone
        holder.itemView.setOnClickListener { view ->
            onContactClick.onClick(view)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    interface OnContactClick {
        fun onClick(view: View)
    }

}
