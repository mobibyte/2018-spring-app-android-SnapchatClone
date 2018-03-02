package mobi.idappthat.snapchat_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by kolten on 2/20/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailEditText;
    EditText passwordEditText;
    Button login;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        login = findViewById(R.id.sign_in_button);
        register = findViewById(R.id.register_button);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                if(validated()) {
                    // login();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.register_button:
                // Go to the register page
                Intent registerIntent = new Intent(this, RegisterActivity.class);
                startActivity(registerIntent);
                // Don't call finish here in case the user changes their mind.
                break;
        }
    }

    private Boolean validated(){
        if(!emailEditText.getText().toString().equals("") && !passwordEditText.getText().toString().equals("")){
            return true;
        }else{
            Toast.makeText(this, "Email or Password is empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void login() {
        // API call to server, create User object and assign values
        // Then start the new activity, pass the user object in the intent
        // Kill this activity with finish
        JsonObject json = new JsonObject();
        try {
            json.addProperty("email", emailEditText.getText().toString());
            json.addProperty("password", passwordEditText.getText().toString());
        } catch (Exception err) {
            Log.d("ERROR", err.toString());
        }
        String url = "localhost:8080/user/login";
        Ion.with(getBaseContext())
                .load(url)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("Login", result.toString());
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }
}
