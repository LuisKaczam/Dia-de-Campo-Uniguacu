package com.uniguacu.dia_de_campo.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.uniguacu.dia_de_campo.Avatar;
import com.uniguacu.dia_de_campo.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Avatar> {
    private final Context context;
    private final ArrayList<Avatar> arrayList;

    public ListViewAdapter(@NonNull Context context, ArrayList<Avatar> arrayList) {
        super(context, R.layout.card_avatar, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View card = inflater.inflate(R.layout.card_avatar, parent, false);
        ImageView avatarLogin = card.findViewById(R.id.avatarLogin);

        avatarLogin.setImageResource(arrayList.get(position).getAvatar());

        return card;
    }

}
