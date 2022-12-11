package com.uniguacu.dia_de_campo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.uniguacu.dia_de_campo.R;


public class MapaFragment extends Fragment {
   private WebView mapa;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapaFragment() {
        // Required empty public constructor
    }


    public static MapaFragment newInstance(String param1, String param2) {
        MapaFragment fragment = new MapaFragment();
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
        View view =inflater.inflate(R.layout.fragment_mapa, container, false);
        mapa = view.findViewById(R.id.webMapa);
        mapa.getSettings().setJavaScriptEnabled(true);
        mapa.setWebViewClient(new WebViewClient());
        mapa.loadUrl("https://drive.google.com/file/d/1HIsQ5o1uGCtTM6cGXTkBIg00ZD9r1MpF/view?usp=share_link");


        return view;
    }
}