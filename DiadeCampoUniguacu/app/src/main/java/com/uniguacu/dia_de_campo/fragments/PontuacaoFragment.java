package com.uniguacu.dia_de_campo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniguacu.dia_de_campo.Codigo;
import com.uniguacu.dia_de_campo.R;
import com.uniguacu.dia_de_campo.Usuario;
import com.uniguacu.dia_de_campo.banco.BancoDados;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PontuacaoFragment extends Fragment {
    private int pontos, idCod;
    private String id;
    private TextView textView;
    private EditText codigo;
    private Button btnInsere;
    private List<Usuario> list;
    private BancoDados bancoDados;
    private List<Codigo> codigoLista;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PontuacaoFragment() {
        // Required empty public constructor
    }


    public static PontuacaoFragment newInstance(String param1, String param2) {
        PontuacaoFragment fragment = new PontuacaoFragment();
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
        View view = inflater.inflate(R.layout.fragment_pontuacao, container, false);
        textView = view.findViewById(R.id.textViewPlacar);
        codigo = view.findViewById(R.id.editTextCodigo);
        btnInsere = view.findViewById(R.id.buttonInserirCod);
        list = new ArrayList<>();
        codigoLista = new ArrayList<>();
        bancoDados = new BancoDados(getContext());

        list = bancoDados.DadosUser();
        for (Usuario usuario : list) {
            pontos = usuario.getPnts();
        }
        textView.setText("Pontuação: " + pontos);


        btnInsere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  idCodigo = UUID.randomUUID().toString();
                codigoLista = bancoDados.DadosCodigo();
                String txt = codigo.getText().toString();
                ArrayList<String> vCod = new ArrayList<>();
                for (Codigo c: codigoLista){
                    vCod.add(c.getCod());
                }
                if (codigo.getText().toString().equals("uzae4") || codigo.getText().toString().equals("6kuyi") || codigo.getText().toString().equals("r6gjj") || codigo.getText().toString().equals("q0p8u") || codigo.getText().toString().equals("uzf2d") || codigo.getText().toString().equals("6wlfq") || codigo.getText().toString().equals("ybhah") || codigo.getText().toString().equals("bku8e") || codigo.getText().toString().equals("oejhb") || codigo.getText().toString().equals("r8hwd") || codigo.getText().toString().equals("bq1wq") || codigo.getText().toString().equals("ngjg7") || codigo.getText().toString().equals("s43sm") || codigo.getText().toString().equals("kg8xa") || codigo.getText().toString().equals("2fi7y") || codigo.getText().toString().equals("w47ra") || codigo.getText().toString().equals("sabx4") || codigo.getText().toString().equals("zhynp") || codigo.getText().toString().equals("cd235") || codigo.getText().toString().equals("eyjnf") || codigo.getText().toString().equals("76rhu") || codigo.getText().toString().equals("ldgiu") || codigo.getText().toString().equals("e77fd") || codigo.getText().toString().equals("3kgjo") || codigo.getText().toString().equals("rqbm7") || codigo.getText().toString().equals("b95go") || codigo.getText().toString().equals("3h890") || codigo.getText().toString().equals("4qqo8") || codigo.getText().toString().equals("5bnwu") || codigo.getText().toString().equals("qat6a") || codigo.getText().toString().equals("9w6g0") || codigo.getText().toString().equals("doz84") || codigo.getText().toString().equals("kctb8") || codigo.getText().toString().equals("g2qmx") || codigo.getText().toString().equals("t854u") || codigo.getText().toString().equals("wrpxx") || codigo.getText().toString().equals("qj7wu") || codigo.getText().toString().equals("z7rjz") || codigo.getText().toString().equals("mzy5p") || codigo.getText().toString().equals("i8jze") || codigo.getText().toString().equals("n5fn1") || codigo.getText().toString().equals("wkzee") || codigo.getText().toString().equals("rf7lb") || codigo.getText().toString().equals("5mzib") || codigo.getText().toString().equals("oooye") || codigo.getText().toString().equals("lrwzd") || codigo.getText().toString().equals("3asdo") || codigo.getText().toString().equals("hm79f") || codigo.getText().toString().equals("kq98e") || codigo.getText().toString().equals("ehbnk"))  {
                    if (vCod.contains(txt)) {
                        Toast.makeText(getContext(), "Código já cadastrado", Toast.LENGTH_SHORT).show();
                    } else {
                        pontos = pontos + 2;
                        Codigo cod = new Codigo();
                        cod.setIdCod(idCodigo);
                        cod.setCod(codigo.getText().toString());
                        bancoDados.InsereCodigo(cod);
                        List<Usuario> usuarioList = new ArrayList<>();
                        usuarioList = bancoDados.DadosUser();
                        for (Usuario usuario : usuarioList) {
                            id = usuario.getId();
                        }
                        bancoDados.UpgradePnts(id, pontos);
                        textView.setText("Pontuação: " + pontos);
                    }
                } else {
                    Toast.makeText(getContext(), "Código Invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}