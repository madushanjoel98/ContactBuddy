package com.example.contactview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactview.R;
import com.example.contactview.dto.Contact;
import com.example.contactview.utils.ColorFun;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<Contact> contacts;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ContactListAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();  // Notify the adapter about the dataset changes
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        //holder.phoneTextView.setText(contact.getPhoneNumber());
        char firstLetter = contact.getName().charAt(0);
        if(contact.getName().length()>20){
            holder.nameTextView.setTextSize(20);
        }
        ColorFun.getInstance().changecorlorByLatter(holder.firstlater,String.valueOf(firstLetter).toUpperCase());
        holder.firstlater.setText(String.valueOf(firstLetter).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,firstlater;
        TextView phoneTextView;
        Button editButton;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstlater=itemView.findViewById(R.id.firstlat);
            nameTextView = itemView.findViewById(R.id.nameTextView);
           // phoneTextView = itemView.findViewById(R.id.phoneTextView);
//            editButton = itemView.findViewById(R.id.editButton);
//            deleteButton = itemView.findViewById(R.id.deleteButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });




        }

    }
}
