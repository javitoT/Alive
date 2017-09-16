package com.example.dell.alive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText editTextuser;
    private EditText editTextpassword;
    private Button buttonlogin;

    private VolleyRP volley;
    private RequestQueue mRequest;

    private static String IP = "http://alivechat.000webhostapp.com/ArchivosPHP/Login_GETID.php?id=";

    private String USER = "";
    private String PASSWORD = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        editTextuser = (EditText) findViewById(R.id.editTextuser);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);

        buttonlogin = (Button) findViewById(R.id.buttonlogin);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyLogin(editTextuser.getText().toString().toLowerCase(),editTextpassword.getText().toString().toLowerCase());
            }
        });
    }

    public void VerifyLogin(String user, String password){
        USER = user;
        PASSWORD = password;
        SolicitudJSON(IP+user);
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject datos) { VerificarPassword(datos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 Toast.makeText(MainActivity.this,"An error ocurred, please contact admin",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volley);
    }

    public void VerificarPassword(JSONObject datos){
        try {
            String estado = datos.getString("resultado");
            if(estado.equals("CC")) {
                JSONObject Jsondatos = new JSONObject(datos.getString("datos"));
                String usuario = Jsondatos.getString("id");
                String contraseña = Jsondatos.getString("Password");
                if (usuario.equals(USER) && contraseña.equals(PASSWORD)) {
                    Toast.makeText(this,"Login Correct", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,NuevaActividad.class);
                    startActivity(i);
                }
                else Toast.makeText(this,"Incorrect Password", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();

            }
        }   catch (JSONException e) {}
    }
}
