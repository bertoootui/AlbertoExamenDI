package com.example.albertoexamendi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    TextView txtview;
    Button butin, butgas,butsal, butsett;
    FloatingActionButton butcal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int[] numin = {0};
        final int[] numgas = {0};
        final int[] numsal = {0};

        butin = findViewById(R.id.butingreso);
        butgas = findViewById(R.id.butgasto);
        butsal = findViewById(R.id.butsaldo);
        butcal = findViewById(R.id.butcal);
        butsett = findViewById(R.id.butsett);
        txtview = findViewById(R.id.txtview);
        SharedPreferences sp = getSharedPreferences("ingresos", Context.MODE_PRIVATE);
        SharedPreferences sp2 = getSharedPreferences("gastos", Context.MODE_PRIVATE);
        SharedPreferences sp3 = getSharedPreferences("saldo",Context.MODE_PRIVATE);

        butin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("INGRESO");
                alertDialog.setMessage("Introduzca la catidad");

                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.ic_launcher_background);

                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString("ingresos" + numin[0],input.getText().toString());
                                edit.commit();
                               // finish();

                                numin[0]++;

                                String name = "saldo" + numsal[0];
                                numsal[0]++;
                                int sal = 0;
                                SharedPreferences.Editor edit2 = sp3.edit();
                                sal = sp3.getInt(name,0);
                                sal = sal + Integer.valueOf(input.getText().toString());
                                edit2.putInt("saldo" + numsal[0],sal);
                                edit2.commit();



                                String s = "";
                                for (int i = 0; i<numin[0];i++)
                                {
                                    s = sp.getString("ingresos"+i,"") +"\n" + s ;


                                }
                                txtview.setText(s);



                            }
                        });

                alertDialog.setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();

            }


        });


        butgas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("GASTO");
                alertDialog.setMessage("Introduzca la catidad");

                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.ic_launcher_background);

                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences.Editor edit = sp2.edit();
                                edit.putString("gastos" + numgas[0],input.getText().toString());
                                edit.commit();

                                numgas[0]++;

                                String name = "saldo" + numsal[0];
                                numsal[0]++;
                                int sal = 0;
                                SharedPreferences.Editor edit2 = sp3.edit();
                                sal = sp3.getInt(name,0);
                                sal = sal - Integer.valueOf(input.getText().toString());
                                edit2.putInt("saldo" + numsal[0],sal);
                                edit2.commit();
                                String s = "";
                                for (int i = 0; i<numgas[0];i++)
                                {
                                    s = sp2.getString("gastos"+i,"") +"\n" + s ;


                                }
                                txtview.setText(s);



                            }
                        });

                alertDialog.setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();

            }


        });

        butsal.setOnClickListener(v -> {
           String s = "";
           String name = "";
            int num = 0;
            for (int i = 1; i<=numsal[0];i++)
            {
                name = "saldo"+i;
                num = sp3.getInt(name,-1);
                s = s+"\n" +num;


            }
            txtview.setText(s);

        });

        butcal.setOnClickListener(v -> {

            Intent i = new Intent(MainActivity.this,CalendarActivity.class);
            startActivity(i);
            finish();

        });

        butsett.setOnClickListener(v -> {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("AJUSTES");
            alertDialog.setMessage("Cambiar color app");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    setTheme(R.style.Theme_AlbertoExamenDI2);
                }
            });
            alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();
        });




    }
}