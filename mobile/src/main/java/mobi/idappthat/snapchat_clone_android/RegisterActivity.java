package mobi.idappthat.snapchat_clone_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameTextView;
    EditText usernameTextView;
    EditText emailTextView;
    EditText passwordTextView;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameTextView = findViewById(R.id.et_name);
        usernameTextView = findViewById(R.id.et_username);
        emailTextView = findViewById(R.id.et_email);
        passwordTextView = findViewById(R.id.et_password);
        registerButton = findViewById(R.id.complete_register_button);

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.complete_register_button:

                break;
        }
    }
}
