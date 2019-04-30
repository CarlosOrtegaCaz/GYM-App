package com.example.gymmf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormularioOxxo extends AppCompatActivity {
    Button Continuar;
    EditText CodigoOxxo;
    SharedPreferences settings, usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_oxxo);

        Continuar = findViewById(R.id.btnPagoOxxo);
        CodigoOxxo = findViewById(R.id.txtCodigoOxxo);


        settings = PreferenceManager.getDefaultSharedPreferences(this);
        usuarios = getSharedPreferences("usuarios", 0);

        Continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CodigoOxxo.getText().toString().equals("")){
                    if(actualizarEstadoPago()){
                        Intent munupayed = new Intent(FormularioOxxo.this,  MainActivity.class);
                        FormularioOxxo.this.startActivity(munupayed);
                        finish();
                    } else {
                        //Aqui deberia mostrar error. Por motivc DEMO continuaremos
                        Intent munupayed = new Intent(FormularioOxxo.this,  MainActivity.class);
                        FormularioOxxo.this.startActivity(munupayed);
                        finish();
                    }

                } else {
                    CodigoOxxo.requestFocus();
                    CodigoOxxo.setError("Requerido");
                }
            }
        });
    }

    public boolean actualizarEstadoPago(){

        //IF Bloque con edicion de usuario actual
        if (!settings.getString("UsuarioActivo", "").equals("")) {
            String avatarActivo = settings.getString("AvatarActivo", "");
            if (!avatarActivo.isEmpty()) {
                try {
                    JSONObject AvatarObj = new JSONObject(avatarActivo);
                    if (AvatarObj.optString("PagoActivo").equals("true")) {
                        Intent intentMenuAutomatico = new Intent(this, MainActivity.class);
                        this.startActivity(intentMenuAutomatico);
                        finish();
                    } else {
                        //Cambiar  PagoActivo a verdadero
                        AvatarObj.put("PagoActivo", "true");
                        settings.edit().putString("AvatarActivo", AvatarObj.toString()).apply();
                        String BDUsuarios = usuarios.getString("BDUsuarios", "");

                        if (BDUsuarios.isEmpty()) {
                            Toast.makeText(this, "Error, falta implementar Bases de datos", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONArray jArrayUsuarios = new JSONArray(BDUsuarios);
                                String user = settings.getString("UsuarioActivo", "");
                                for (int i = 0; i < jArrayUsuarios.length(); i++) {
                                    JSONObject usuarioObj = new JSONObject(jArrayUsuarios.get(i).toString());
                                    if (user.equals(usuarioObj.getString("Usuario"))) {
                                        jArrayUsuarios.put(i, AvatarObj);
                                        usuarios.edit().putString("BDUsuarios", jArrayUsuarios.toString()).apply();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
