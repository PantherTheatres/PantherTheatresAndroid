package team383.edu.panthertheatres;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements View.OnClickListener {

    // Initialize Variables
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;

    // Initiatialize Firebase Authentication
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        // Connect Text Variables to Views in Layout
        editTextEmail = getView().findViewById(R.id.editTextEmail);
        editTextPassword = getView().findViewById(R.id.editTextPassword);
        progressBar = getView().findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        // Set ClickListener to Signup and Login buttons
        getView().findViewById(R.id.buttonSignUp).setOnClickListener(this);
        getView().findViewById(R.id.textViewLogin).setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.textViewSignup:
                Fragment fragment = new SignUpFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
                break;
            case R.id.buttonLogin:
                userLogin();

                // Once the login is validated, switch to the movie feedback fragment
                /* Fragment fragment = new FeedbackFragment();
                getGragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();

                */

                break;
        }
    }

    private void userLogin()
    {
        // Remove leading and trailing whitespace from both text inputs
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Input validation
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email.");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password is 6");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }
}