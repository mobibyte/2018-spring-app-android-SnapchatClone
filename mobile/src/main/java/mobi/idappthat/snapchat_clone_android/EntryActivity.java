package mobi.idappthat.snapchat_clone_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mobi.idappthat.snapchat_clone_android.Classes.User;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        // We want to check if the user has previously authed or not
        // Access the SharedPrefs
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String token = sharedPref.getString(String.valueOf(R.string.token), "jwt");

        // Probably not the best practice
        if(!token.equals("jwt")){
            // We have a token from a previous
            // time the app was open
            // Need to check if the token is still valid
            // Get back user info and assign it to an User object
        }else{
            // The user has never been authed, new user
            // Go to the login activity and kill this activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
