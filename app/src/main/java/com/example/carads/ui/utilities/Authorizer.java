package com.example.carads.ui.utilities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carads.R;
import com.example.carads.ui.callbacks.FuncVoid;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Максим on 25.01.2018.
 */

//public class Authorizer {
//
//    private FirebaseAuth mAuth;
//    private Context context;
//    private ProgressManager dialogProgress;
//
//    public Authorizer(FirebaseAuth mAuth, Context context) {
//        this.mAuth = mAuth;
//        this.context = context;
//        dialogProgress = new ProgressManager(context);
//    }
//
//
//
//    //зарегистрироваться
//    public void createAccount(String email, String password) {
//        Log.d("createAccount", "createAccount:" + email);
//        if (!validateForm()) {
//            return;
//        }
//
//        dialogProgress.showProgressDialog();
//
//        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((AppCompatActivity)context,this::createUser);
//
//    }
//
//    private void createUser(Task<AuthResult> task){
//
//        if (task.isSuccessful()) {
//            Log.d("createUser", "createUserWithEmail:success");
//            FirebaseUser user = mAuth.getCurrentUser();
//            updateUI(user);
//        } else {
//
//            Log.w("createUser", "createUserWithEmail:failure", task.getException());
//            showMessage(context.getString(R.string.authentication_failed));
//            updateUI(null);
//        }
//        dialogProgress.hideProgressDialog();
//
//    }
//
//
//
//
//    //Войти,если ранее зарегистрировались
//    public void signIn(String email, String password,FuncVoid funcVoid,FuncVoid funcVoid2) {
//        Log.d("signIn", "signIn:" + email);
//        if (!validateForm()) {
//            return;
//        }
//
//        dialogProgress.showProgressDialog();
//
//        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener((AppCompatActivity)context,task-> signInUser(task,() -> funcVoid.action(),() -> funcVoid2.action()));
//
//    }
//
//    private void signInUser(Task<AuthResult> task,FuncVoid funcVoid,FuncVoid funcVoid2){
//
//        if (task.isSuccessful()) {
//            // Sign in success, update UI with the signed-in user's information
//            Log.d("signInUser", "signInWithEmail:success");
//            FirebaseUser user = mAuth.getCurrentUser();
//            updateUI(user,() -> funcVoid.action());
//        } else {
//            // If sign in fails, display a message to the user.
//            Log.w("signInUser", "signInWithEmail:failure", task.getException());
//            showMessage(context.getString(R.string.authentication_failed));
//            updateUI(null, () -> funcVoid.action());
//        }
//        if (!task.isSuccessful()) {
//            funcVoid2.action();
//           // tvStatus.setText(R.string.authentication_failed);
//        }
//        dialogProgress.hideProgressDialog();
//
//    }
//
//
//    //выйти из регистрации
//    public void signOut(FuncVoid funcVoid) {
//        mAuth.signOut();
//        updateUI(null,() ->{
//
//            funcVoid.action();
//        });
//    }
//
//
//
//    //отправление сообщения по электронной почте
//    public void sendEmailVerification() {
//
//        findViewById(R.id.verify_email_button).setEnabled(false);
//
//        final FirebaseUser user = mAuth.getCurrentUser();
//
//        user.sendEmailVerification().addOnCompleteListener((AppCompatActivity)context,task ->sendEmail(task,user) );
//
//    }
//
//
//    private void sendEmail(Task<Void> task, FirebaseUser user){
//
//        findViewById(R.id.verify_email_button).setEnabled(true);
//
//        if (task.isSuccessful()) {
//            showMessage(context.getString(R.string.send_verif_email)+" "+user.getEmail());
//        } else {
//            Log.e("sendEmail", "sendEmailVerification", task.getException());
//            showMessage(context.getString(R.string.failed_send_verification_email));
//        }
//
//    }
//
//
//    private boolean validateForm() {
//        boolean valid = true;
//
//        if (TextUtils.isEmpty(email.getText().toString())) {
//            email.setError("Required.");
//            valid = false;
//        } else {
//            email.setError(null);
//        }
//
//
//        if (TextUtils.isEmpty(password.getText().toString())) {
//            password.setError("Required.");
//            valid = false;
//        } else {
//            password.setError(null);
//        }
//        return valid;
//    }
//
//
//
//    private void updateUI(FirebaseUser currentUser, FuncVoid funcVoid) {
//        dialogProgress.hideProgressDialog();
//
//
//        funcVoid.action();
//
////        if(currentUser!=null){
////            tvStatus.setText(getString(R.string.emailpassword_status_fmt,
////                    currentUser.getEmail(), currentUser.isEmailVerified()));
////
////            findViewById(R.id.email_passward_content).setVisibility(View.GONE);
////            findViewById(R.id.signed_content).setVisibility(View.VISIBLE);
////            findViewById(R.id.verify_email_button).setEnabled(!currentUser.isEmailVerified());
////
////        }else{
////
////            tvStatus.setText(R.string.signed_out);
////
////            findViewById(R.id.email_passward_content).setVisibility(View.VISIBLE);
////
////            findViewById(R.id.signed_content).setVisibility(View.GONE);
////        }
//    }
//
//
//    private void showMessage(String message){
//
//        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
//
//    }
//
//
//}
