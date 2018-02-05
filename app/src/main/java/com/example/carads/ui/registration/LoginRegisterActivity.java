package com.example.carads.ui.registration;

import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.carads.R;
import com.example.carads.di.App;
import com.example.carads.ui.utilities.ProgressManager;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import javax.inject.Inject;

public class LoginRegisterActivity extends AppCompatActivity {

    private TextView tvStatus;
    private EditText email;
    private EditText password;
    //private FirebaseAuth mAuth;
    private ProgressManager dialogProgress;

    @Inject
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        initComponents();
       // initFirebase();

    }

//    private void initFirebase(){
//
//        mAuth = FirebaseAuth.getInstance();
//    }

    private void initComponents() {

        App.getAppComponent().injectLoginRegisterActivity(this);

        tvStatus=(TextView)findViewById(R.id.tvStatus);
        email=((TextInputLayout)findViewById(R.id.email_input)).getEditText();
        password=((TextInputLayout)findViewById(R.id.password_input)).getEditText();

        Button btnSign =(Button)findViewById(R.id.sign_in_button);
        Button btnRegister =(Button)findViewById(R.id.register_button);
        Button btnSignOut =(Button)findViewById(R.id.sign_out_button);
        Button btnVerifyEmail =(Button)findViewById(R.id.verify_email_button);


        btnSign.setOnClickListener(this::setListener);
        btnRegister.setOnClickListener(this::setListener);
        btnSignOut.setOnClickListener(this::setListener);
        btnVerifyEmail.setOnClickListener(this::setListener);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbarRegister);
                setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        toolbar.setTitle(R.string.register_user);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorWhite));

        dialogProgress = new ProgressManager(this);
    }

    private void setListener(View v){

        switch (v.getId()){

            case R.id.sign_in_button:
                signIn(email.getText().toString(), password.getText().toString());
                break;
            case R.id.register_button:
                createAccount(email.getText().toString(), password.getText().toString());
                break;
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.verify_email_button:
                sendEmailVerification();
                break;
        }
    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


             //зарегистрироваться
    private void createAccount(String email, String password) {
        Log.d("createAccount", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        dialogProgress.showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,this::createUser);

    }

private void createUser(Task<AuthResult> task){

    if (task.isSuccessful()) {
        Log.d("createUser", "createUserWithEmail:success");
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    } else {

        Log.w("createUser", "createUserWithEmail:failure", task.getException());
        showMessage(getString(R.string.authentication_failed));
        updateUI(null);
    }
    dialogProgress.hideProgressDialog();

}




          //Войти,если ранее зарегистрировались
    private void signIn(String email, String password) {
        Log.d("signIn", "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        dialogProgress.showProgressDialog();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,this::signInUser);

    }

private void signInUser(Task<AuthResult> task){

    if (task.isSuccessful()) {
        // Sign in success, update UI with the signed-in user's information
        Log.d("signInUser", "signInWithEmail:success");
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    } else {
        // If sign in fails, display a message to the user.
        Log.w("signInUser", "signInWithEmail:failure", task.getException());
        showMessage(getString(R.string.authentication_failed));
        updateUI(null);
    }


    if (!task.isSuccessful()) {
        tvStatus.setText(R.string.authentication_failed);
    }
    dialogProgress.hideProgressDialog();

}


        //выйти из регистрации
    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }



    //отправление сообщения по электронной почте
    private void sendEmailVerification() {

        findViewById(R.id.verify_email_button).setEnabled(false);

        final FirebaseUser user = mAuth.getCurrentUser();

        user.sendEmailVerification().addOnCompleteListener(this,task ->sendEmail(task,user) );

    }


    private void sendEmail(Task<Void> task, FirebaseUser user){

        findViewById(R.id.verify_email_button).setEnabled(true);

        if (task.isSuccessful()) {
            showMessage(getString(R.string.send_verif_email)+" "+user.getEmail());
        } else {
            Log.e("sendEmail", "sendEmailVerification", task.getException());
            showMessage(getString(R.string.failed_send_verification_email));
        }

    }


    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }


        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }
        return valid;
    }



    private void updateUI(FirebaseUser currentUser) {
        dialogProgress.hideProgressDialog();

          if(currentUser!=null){
              tvStatus.setText(getString(R.string.emailpassword_status_fmt,
                      currentUser.getEmail(), currentUser.isEmailVerified()));

              findViewById(R.id.email_passward_content).setVisibility(View.GONE);
              findViewById(R.id.signed_content).setVisibility(View.VISIBLE);
              findViewById(R.id.verify_email_button).setEnabled(!currentUser.isEmailVerified());

          }else{

              tvStatus.setText(R.string.signed_out);

              findViewById(R.id.email_passward_content).setVisibility(View.VISIBLE);

              findViewById(R.id.signed_content).setVisibility(View.GONE);
          }
    }


    private void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

}
