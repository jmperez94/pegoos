package com.asesoftware.peggos;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.asesoftware.peggos.persistence.dao.UserDao;
import com.asesoftware.peggos.persistence.dao.UserDaoImpl;
import com.asesoftware.peggos.persistence.dto.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TableLayout userList = findViewById(R.id.userList);
        fillTable(userList);
    }

    public void fillTable(final TableLayout userList) {
        final UserDaoImpl db = new UserDaoImpl(getBaseContext());
        ArrayList<User> users = db.getAllUsers();

        for (final User user : users) {
            TableRow row = new TableRow(this);
            row.setPadding(60, 30, 60, 15);
            row.setGravity(Gravity.CENTER);
            ImageButton delete = new ImageButton(this, null, R.style.st_image_button);
            delete.setLayoutParams(new TableRow.LayoutParams(70, 50));
            delete.setImageResource(R.drawable.trash);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder inputDelete = createDialog("Eliminar", "¿Confirma la eliminación?", user.getNombre(), deleteUser(user, db, userList), null);
                    inputDelete.show();
                }
            });
            TextView tv_nombre = generateTextView(user.getNombre(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder inputEdit = createDialog("Editar nombre", "Ingrese el nombre", user.getNombre(), null, null);
                    inputEdit.setPositiveButton("Ok", editUser(inputEdit, user, db, userList));
                    inputEdit.show();
                }
            });
            row.addView(tv_nombre);
            row.addView(delete);
            userList.addView(row);
        }
    }

    public void addUser(View v) {
        final UserDaoImpl db = new UserDaoImpl(getBaseContext());
        AlertDialog.Builder addAlert = createDialog("Agregar", "Por favor ingrese el nombre", null, null, null);
        final EditText input = new EditText(MainActivity.this);
        addAlert.setView(input);
        final TableLayout userList = findViewById(R.id.userList);
        addAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText();
                final User user = new User(value.toString());
                db.addUser(user);
                userList.removeAllViews();
                fillTable(userList);
            }
        });
        addAlert.show();
    }

    public DialogInterface.OnClickListener deleteUser(final User user, final UserDaoImpl db, final TableLayout userList) {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                db.deleteUser(user);
                userList.removeAllViews();
                fillTable(userList);
            }
        };
    }

    public DialogInterface.OnClickListener editUser(final AlertDialog.Builder alert, final User user, final UserDao db, final TableLayout userList) {
        final EditText input = new EditText(MainActivity.this);
        input.setText(user.getNombre());
        alert.setView(input);
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user.setNombre(input.getText().toString());
                db.updateUser(user);
                userList.removeAllViews();
                fillTable(userList);
            }
        };
    }

    public AlertDialog.Builder createDialog(String titulo, String mensaje, String preInput, DialogInterface.OnClickListener aceptar, DialogInterface.OnClickListener cancelar) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this, R.style.st_alertas);
        alert.setTitle(titulo);
        alert.setMessage(mensaje);
        alert.setPositiveButton("Ok", aceptar);
        alert.setNegativeButton("Cancel", cancelar);
        return alert;
    }

    @SuppressLint("ClickableViewAccessibility")
    public TextView generateTextView(String texto, View.OnClickListener accion) {
        TextView tv_texto = new TextView(getBaseContext());
        tv_texto.setText(texto);
        tv_texto.setPadding(0, 0, 20, 0);
        tv_texto.setLayoutParams(new TableRow.LayoutParams(350, 70));
        tv_texto.setGravity(Gravity.CENTER);
        tv_texto.setOnClickListener(accion);
        return tv_texto;
    }


    public void regresar(View v) {
        Intent regresar = new Intent(this, Principal.class);
        startActivity(regresar);
    }
}