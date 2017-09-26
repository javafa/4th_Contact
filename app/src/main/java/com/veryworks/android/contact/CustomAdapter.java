package com.veryworks.android.contact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 9/26/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{
    List<Contact> data;

    public void setDataAndRefresh(List<Contact> data){
        this.data = data;
        // 데이터가 변경되었음을 알린다
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Contact contact = data.get(position);
        holder.setNumber(contact.getNumber());
        holder.setTextName(contact.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private String number;
        private TextView textNumber;
        private TextView textName;
        private ImageButton btnCall;
        public Holder(View v) {
            super(v);
            textNumber = (TextView) v.findViewById(R.id.textNumber);
            textName = (TextView) v.findViewById(R.id.textName);
            btnCall = (ImageButton) v.findViewById(R.id.btnCall);
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num = "tel:" + number;
                    Uri uri = Uri.parse(num);
                    Intent intent = new Intent("android.intent.action.CALL",uri);
                    view.getContext().startActivity(intent);
                }
            });
        }
        public void setNumber(String number){
            this.number = number;
            textNumber.setText(this.number);
        }
        public void setTextName(String name){
            textName.setText(name);
        }
    }
}
