package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int poziom = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // pobranie przycisku
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
                dialogBuilder.setTitle("Informacja");
                dialogBuilder.setMessage("Witaj w aplikacji");
                dialogBuilder.setCancelable(false);
                dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Okienko zostalo zamkniete",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            }
        });

        //poziomy trudnosci
        Button button1 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] pozycje = {"łatwy", "średni", "trudny"};
                final int[] temp_poziom = new int[1];

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setTitle("Wybierz poziom trudności");
                builder.setSingleChoiceItems(pozycje, poziom, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       temp_poziom[0] = i;
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        poziom = temp_poziom[0];
                        Toast.makeText(getBaseContext(),"Wybrano poziom "+ pozycje[poziom],
                                Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Zrezygnowano ze zmiany poziomu",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    public void OknoDialogowe(View view){
        Log.d("zdarznie", "kliknieto przycisk");

        final ProgressDialog dialog = ProgressDialog.show(
                this, "Realizuję zadanie", "Proszę czekać...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }).start();

    }

    public void OknoDialogowe2(View view){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("Pobieram dane....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Kliknięto okej", Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Anuluj",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Kliknięto anuluj", Toast.LENGTH_LONG).show();
            }
        });

        progressDialog.show();

        // przesuwanie postepu
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100; i++){
                    try {
                        Thread.sleep(150);
                        progressDialog.incrementProgressBy(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}