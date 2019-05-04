package com.example.gymmf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String NombreUsuario, GeneroUsuario;
    SharedPreferences settings;
    Button Logout, Casa, GYM, Dieta;
    TextView Bienvenida;
    RelativeLayout RLMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        Bienvenida = findViewById(R.id.txtMainWelcome);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println("Settings Main: " + settings.getAll().toString());

        NombreUsuario = settings.getString("NombreActivo", "");
        GeneroUsuario = settings.getString("GeneroActivo", "Hombre");

        RLMain = findViewById(R.id.RelativeLayoutMain);

        Bienvenida.setText("Bienvenido " + NombreUsuario);
        if(GeneroUsuario.equals("Mujer")){
            RLMain.setBackground(ContextCompat.getDrawable(this, R.drawable.main_background_mujer));
            Bienvenida.setText("Bienvenida " + NombreUsuario);
        } else {
            RLMain.setBackground(ContextCompat.getDrawable(this, R.drawable.main_background_hombre));
            Bienvenida.setText("Bienvenido " + NombreUsuario);
        }

       // System.out.println("NOMMRE " + NombreUsuario);


        Logout = findViewById(R.id.btnLogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().remove("NombreActivo").apply();
                settings.edit().remove("UsuarioActivo").apply();
                settings.edit().remove("GeneroActivo").apply();
                settings.edit().remove("AvatarActivo").apply();
                Intent logout = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(logout);
                finish();
            }
        });

        Dieta = findViewById(R.id.btnMainDieta);
        Dieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDieta = new Intent(MainActivity.this, Dietas.class);
                MainActivity.this.startActivity(intentDieta);
            }
        });

    }
}
