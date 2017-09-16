package com.example.dell.alive.Mensajeria;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.alive.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 07/09/2017.
 */

public class Mensajeria extends AppCompatActivity {

    private RecyclerView rv;
    private Button bTEnviarmensaje;
    private EditText eTEscribirMensaje;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajesAdapter adapter;
    private int TEXT_LINES = 1;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        mensajeDeTextos = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        bTEnviarmensaje = (Button) findViewById(R.id.bTenviarMensaje);
        eTEscribirMensaje = (EditText) findViewById(R.id.eTEscribirMensaje);

        rv = (RecyclerView) findViewById(R.id.rvMensajes);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        for(int i=0;i<10;i++){
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId(""+i);
            mensajeDeTextoAuxiliar.setMensaje("El emisor numero "+i+ "te ha enviado un mensaje");
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:2"+i);
            mensajeDeTextoAuxiliar.setTipoMensaje(1);
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        for(int i=0;i<10;i++){
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId(""+i);
            mensajeDeTextoAuxiliar.setMensaje("El receptor numero "+i+ "te ha enviado un mensaje");
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:2"+i);
            mensajeDeTextoAuxiliar.setTipoMensaje(2);
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        adapter = new MensajesAdapter(mensajeDeTextos,this);
        rv.setAdapter(adapter);

        eTEscribirMensaje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(eTEscribirMensaje.getLayout().getLineCount()!=TEXT_LINES){
                    setScrollbarChat();
                    TEXT_LINES = eTEscribirMensaje.getLayout().getLineCount();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bTEnviarmensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Createmensaje(eTEscribirMensaje.getText().toString());
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Createmensaje(String mensaje){
        MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
        mensajeDeTextoAuxiliar.setId("0");
        mensajeDeTextoAuxiliar.setMensaje(mensaje);
        mensajeDeTextoAuxiliar.setHoraDelMensaje("10:20");
        mensajeDeTextoAuxiliar.setTipoMensaje(1);
        mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        adapter.notifyDataSetChanged();
        eTEscribirMensaje.setText("");
        setScrollbarChat();
    }

    public void setScrollbarChat(){
        rv.scrollToPosition(adapter.getItemCount()-1);
    }

}

