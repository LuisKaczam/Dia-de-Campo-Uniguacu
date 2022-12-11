package com.uniguacu.dia_de_campo.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniguacu.dia_de_campo.R;
import com.uniguacu.dia_de_campo.Usuario;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingViewHolder> {
    Context context;
    List<Usuario> usuariosList;

    public RankingAdapter(Context context, List<Usuario> usuariosList) {
        this.context = context;
        this.usuariosList = usuariosList;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_ranking, parent, false);
        return new RankingViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        Usuario usuario = usuariosList.get(position);
        holder.Cardnome.setText("" +usuario.getNome());
        holder.Cardpnts.setText("Pontos: " +usuario.getPnts());
        holder.CardId.setText("id: " + "#" + usuario.getId());
        holder.CardPosicao.setText(""+ (position + 1) + " º ");
        int avatar = usuario.getFoto();
        switch (avatar){
            case 0:
                holder.avatar.setImageResource(R.drawable.avatarl);
                break;
            case 1:
                holder.avatar.setImageResource(R.drawable.avatarll);
                break;
            case 2:
                holder.avatar.setImageResource(R.drawable.avatarlll);
                break;
            case 3:
                holder.avatar.setImageResource(R.drawable.avatarllll);
                break;
            default:
                holder.avatar.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }
}
