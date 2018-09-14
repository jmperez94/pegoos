package com.asesoftware.peggos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asesoftware.peggos.persistence.dao.UserDao;
import com.asesoftware.peggos.persistence.dao.UserDaoImpl;
import com.asesoftware.peggos.persistence.dto.User;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    private static int dice;
    ArrayList<User> users;
    private UserDao user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageButton dice = findViewById(R.id.dice);
        dice.setImageResource(Principal.getDice());

        user = new UserDaoImpl(this);

        users = user.getAllUsers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageButton dice = findViewById(R.id.dice);
        dice.setImageResource(Principal.getDice());
        users = user.getAllUsers();
    }

    public void goToUsers(View v) {
        Intent users = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(users);
    }

    public void goToSettings(View v) {
        Intent principal = new Intent(getApplicationContext(), Setting.class);
        startActivity(principal);
    }

    public static int getDice() {
        if (dice > 0)
            return dice;
        else return R.drawable.dice_orange;
    }

    public static void setDice(int dice) {
        Principal.dice = dice;
    }

    public void userRandom(View v) {
        int random = (int) (Math.random() * users.size());
        TextView winner = findViewById(R.id.winner);
        try {
            winner.setText(users.get(random).getNombre());
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(this, "Por favor valide que tenga participantes", Toast.LENGTH_SHORT);
            //Intent return =new Intent(this, Principal.class);
            //startActivity( return);
        }
    }
}
