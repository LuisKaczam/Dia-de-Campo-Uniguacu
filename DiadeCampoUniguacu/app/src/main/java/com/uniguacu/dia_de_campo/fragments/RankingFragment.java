package com.uniguacu.dia_de_campo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniguacu.dia_de_campo.R;
import com.uniguacu.dia_de_campo.Usuario;
import com.uniguacu.dia_de_campo.recyclerView.RankingAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RankingFragment extends Fragment {
    private DatabaseReference databaseReference;
    private RankingAdapter adapter;
    private List<Usuario> listaRanking;
    private RecyclerView recyclerViewRanking;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RankingFragment() {
        // Required empty public constructor
    }


    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerViewRanking = view.findViewById(R.id.recyclerViewRanking);
        listaRanking = new ArrayList<>();
        recyclerViewRanking.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        recyclerViewRanking.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new RankingAdapter(getContext(), listaRanking);
        recyclerViewRanking.setAdapter(adapter);

        databaseReference.child("Usuarios").orderByChild("nome").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaRanking.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    Usuario usuario = data.getValue(Usuario.class);
                    listaRanking.add(usuario);
                }
                Collections.sort(listaRanking);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Erro ao carregar Ranking", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

}