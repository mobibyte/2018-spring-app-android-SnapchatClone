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

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import mobi.idappthat.snapchat_clone_android.Utils.HttpCallback;
import mobi.idappthat.snapchat_clone_android.Utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by kolten on 2/20/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    EditText emailEditText;
    EditText passwordEditText;
    Button login;
    Button register;
    OkHttpClient client;
    Request request;

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
        client = new OkHttpClient();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                if(validated()) {
                    login();
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
        JSONObject json = new JSONObject();
        try {
            json.put("email", emailEditText.getText().toString());
            json.put("password", passwordEditText.getText().toString());
        } catch (Exception err) {
            Log.d("ERROR", err.toString());
        }
        String jsontoString = json.toString();
        HttpUtil httpUtil = new HttpUtil();
        String url = "http://localhost:8080";
        httpUtil.post(url, jsontoString, new HttpCallback() {
            @Override
            public void onFailure(Response response, Throwable throwable) {

            }

            @Override
            public void onSuccess(final Response response) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }
}
