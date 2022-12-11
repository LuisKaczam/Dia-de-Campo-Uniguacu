package com.uniguacu.dia_de_campo.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniguacu.dia_de_campo.R;

public class RankingViewHolder extends RecyclerView.ViewHolder {
    TextView Cardnome;
    TextView Cardpnts;
    TextView CardId;
    TextView CardPosicao;
    ImageView avatar;
    RankingAdapter adapter;

    public RankingViewHolder(@NonNull View itemView) {
        super(itemView);

        Cardnome = itemView.findViewById(R.id.CardNome);
        Cardpnts = itemView.findViewById(R.id.CardPnts);
        CardId = itemView.findViewById(R.id.textViewId);
        CardPosicao = itemView.findViewById(R.id.textviewPosicao);
        avatar = itemView.findViewById(R.id.imageViewAvatar);
    }

    public RankingViewHolder linkAdapter(RankingAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
