package com.uniguacu.dia_de_campo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.uniguacu.dia_de_campo.banco.BancoDados;
import com.uniguacu.dia_de_campo.recyclerView.ListViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText nomeUser;
    Button btnEntrar;
    BancoDados db;
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    List<Usuario> usuarioList;
    GridView listViewLogin;
    int foto;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nomeUser = findViewById(R.id.editTextNomeUser);
        btnEntrar = findViewById(R.id.buttonEntrar);
        listViewLogin = findViewById(R.id.listViewLogin);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("mmss");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        db = new BancoDados(getApplicationContext());
        usuarioList = new ArrayList<>();
        ArrayAdapter adapter = new ListViewAdapter(this, escolhaAvatares());
        listViewLogin.setAdapter(adapter);
        foto = 5;
        btnEntrar.setEnabled(false);

        listViewLogin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                foto = i;
                if (nomeUser.length() > 2) {
                    btnEntrar.setEnabled(true);
                }
                }
            });


            usuarioList =db.DadosUser();
        if(!usuarioList.isEmpty())

            {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else

            {
                btnEntrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (nomeUser.length() > 2) {
                            String id = dateTime;
                            if(verificaConexao()) {
                                cadastroUser(id, nomeUser.getText().toString(), 0, foto);
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Verifique sua conexão com a Internet", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Login.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        private void cadastroUser (String id, String nome,int pontos, int foto){
            BancoDados db = new BancoDados(getApplicationContext());
            Usuario usuario = new Usuario(id, nome, pontos, foto);
            db.InsereUser(usuario);
            Toast.makeText(Login.this, "Bem-Vindo (a)!", Toast.LENGTH_SHORT).show();
            db.close();
        }
        private ArrayList<Avatar> escolhaAvatares () {
            ArrayList<Avatar> avatarList = new ArrayList<Avatar>();
            avatarList.add(new Avatar(R.drawable.avatarl));
            avatarList.add(new Avatar(R.drawable.avatarll));
            avatarList.add(new Avatar(R.drawable.avatarlll));
            avatarList.add(new Avatar(R.drawable.avatarllll));


            return avatarList;

        }

        public boolean verificaConexao () {
            ConnectivityManager conexão = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
            return conexão.getActiveNetworkInfo() != null && conexão.getActiveNetworkInfo().isConnectedOrConnecting();
        }
    }