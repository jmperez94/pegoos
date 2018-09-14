package com.asesoftware.peggos;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Resources res = getResources();

        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();

        tabs.addTab(createSpec(tabs,"BOTONES",R.id.tab1));
        tabs.addTab(createSpec(tabs,"COLORES",R.id.tab2));

        tabs.setCurrentTab(0);

        TableLayout image_table = findViewById(R.id.images_to_buttons);
        ArrayList<Integer> imageOptions = getImages();
        fillImageSelector(imageOptions, 3, image_table);
    }

    public TabHost.TabSpec createSpec(TabHost tabs, String texto,int content) {
        TabHost.TabSpec spec = tabs.newTabSpec("tabSpec");
        spec.setContent(content);
        spec.setIndicator(texto, null);
        return spec;
    }

    public void fillImageSelector(final ArrayList<Integer> imageOptions, int columnas, TableLayout image_table) {
        int filas = imageOptions.size() / columnas;

        for (int i = 0; i < filas; i++) {

            TableRow row = new TableRow(this);
            for (int j = 0; j < columnas; j++) {
                ImageButton test = new ImageButton(this, null, R.style.st_image_button);
                test.setLayoutParams(new TableRow.LayoutParams(0, 200));
                test.setImageResource(imageOptions.get(j + 3 * i));
                final int finalJ = j;
                final int finalI = i;
                test.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Principal.setDice(imageOptions.get(finalJ + 3 * finalI));
                        Toast.makeText(getBaseContext(), "Cambio relizado", Toast.LENGTH_SHORT).show();
                    }
                });
                row.addView(test);

            }
            image_table.addView(row);
        }
    }

    public ArrayList<Integer> getImages() {
        ArrayList<Integer> imageOptions = new ArrayList<>();
        imageOptions.add(R.drawable.dice_orange);
        imageOptions.add(R.drawable.dice_black);
        imageOptions.add(R.drawable.dice_blue);
        imageOptions.add(R.drawable.dice_morado);
        imageOptions.add(R.drawable.dice_yellow);
        imageOptions.add(R.drawable.dice_pink);
        return imageOptions;
    }

    public void regresar(View v) {
        Intent regresar = new Intent(this, Principal.class);
        startActivity(regresar);
    }
}
