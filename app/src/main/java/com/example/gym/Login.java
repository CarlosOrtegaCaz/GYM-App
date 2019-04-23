package com.example.gym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button btnRegistrar, btnLogin;
    EditText User, Pass;
    SharedPreferences usuarios, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegistrar = findViewById(R.id.btnRegistrate);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlan = new Intent(Login.this, Planes.class);
                Login.this.startActivity(intentPlan);
            }
        });

        User = findViewById(R.id.txtName);
        Pass = findViewById(R.id.txtPass);

        usuarios = getSharedPreferences("usuarios", 0);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println("Settings: " + settings.getAll().toString());
        if(!settings.getString("UsuarioActivo","").equals("")){
            String avatar = settings.getString("AvatarActivo", "");
            if(!avatar.isEmpty()){
                try {
                    JSONObject AvatarObj = new JSONObject(avatar);
                    if(AvatarObj.optString("PagoActivo").equals("true")){
                        Intent intentMenuAutomatico = new Intent(Login.this, MainActivity.class);
                        Login.this.startActivity(intentMenuAutomatico);
                        finish();
                    } else if (AvatarObj.optString("DemoActivo").equals("true")){
                        Intent intentMenuAutomatico = new Intent(Login.this, MainActivity.class);
                        Login.this.startActivity(intentMenuAutomatico);
                        finish();
                    } else {
                        Intent intentMenuAutomatico = new Intent(Login.this, Planes.class);
                        Login.this.startActivity(intentMenuAutomatico);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }


        //if (settings.getBoolean("FirstTime", true)) {
            //settings.edit().putBoolean("FirstTime", false);
            //JSONObject esqueletoUsuarios = new JSONObject();
            //usuarios
        //}


        btnLogin = findViewById(R.id.btnIngresar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validarCred())
                    return;
                if(login()){
                    Intent intentMenu = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(intentMenu);
                    finish();
                }
            }
        });
        //if (avatar.getAll().toString().equals(""))
    }

    public boolean validarCred(){
        String user = User.getText().toString();
        String pass = Pass.getText().toString();
        if(user.equals("")){
            User.requestFocus();
            User.setError("Obligatorio");
            return false;
        }
        if(pass.equals("")){
            Pass.requestFocus();
            Pass.setError("Obligatorio");
            return false;
        }
        return true;
    }

    public boolean login() {
        String user = User.getText().toString();
        String pass = Pass.getText().toString();
        String BDUsuarios = usuarios.getString("BDUsuarios", "");

        if (BDUsuarios.isEmpty()) {
            User.requestFocus();
            User.setError("Usuario no registrado");
            return false;
        } else {
            try {
                JSONArray jArrayUsuarios = new JSONArray(BDUsuarios);
                System.out.println("USUs: " + BDUsuarios + " L: " + jArrayUsuarios.length());
                for (int i = 0; i < jArrayUsuarios.length(); i++) {
                    System.out.println("Usuario#" + i + " " + jArrayUsuarios.get(i));
                    JSONObject usuarioObj = new JSONObject(jArrayUsuarios.get(i).toString());
                    if (user.equals(usuarioObj.getString("Usuario")) && pass.equals(usuarioObj.getString("Pass"))) {
                        settings.edit().putString("UsuarioActivo", user).apply();
                        settings.edit().putString("NombreActivo", usuarioObj.getString("Nombre")).apply();
                        settings.edit().putString("GeneroActivo", usuarioObj.getString("Genero")).apply();
                        settings.edit().putString("AvatarActivo", usuarioObj.toString()).apply();
                        return true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        User.requestFocus();
        User.setError("Usuario o contraseña incorrectos");
        return false;
    }
}
