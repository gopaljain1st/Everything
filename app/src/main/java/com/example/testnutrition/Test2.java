package com.example.testnutrition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 extends AppCompatActivity
{

   private static final String TAG="FACELOG";
    private static final int RC_SIGN_IN =0 ;
    Button signInWithFacebook,signInWithGoogle,mobilesignUp;
    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    EditText mobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        signInWithFacebook=findViewById(R.id.signInWithFacebook);
        signInWithGoogle=findViewById(R.id.signInWithGoogle);
        mobilesignUp=findViewById(R.id.mobileSignUp);
        mobileNo=findViewById(R.id.userMobileNo);
        mobilesignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Pattern p = Pattern.compile("(0|91)?[6789][0-9]{9}");
                Matcher m = p.matcher(mobileNo.getText().toString().trim());
               if(mobileNo.getText().toString().trim().equals(""))
                   mobileNo.setError("Mobile No Required");
               else if(m.find()&&m.group().equals(mobileNo.getText().toString().trim()))
               {
                   Intent in = new Intent(Test2.this,Register.class);
                   in.putExtra("mobileNo",mobileNo.getText().toString());
                   in.putExtra("check","mobile");
                   startActivity(in);
                   finish();
               }
            }
        });


        callbackManager=CallbackManager.Factory.create();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signInWithGoogle:
                        signIn();
                        break;
                }
            }
        });
        signInWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(Test2.this, Arrays.asList("public_profile","email","mobile_phone"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response)
                                    {
                                        Toast.makeText(Test2.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                                        Log.v("LoginActivity", response.toString());
                                        // Application code
                                        try {


                                            Intent in=new Intent(Test2.this,Register.class);
                                            in.putExtra("check","facebook");
                                            in.putExtra("name",object.getString("name"));
                                            in.putExtra("email",object.getString("email"));
                                            in.putExtra("id",object.getString("id"));
                                            in.putExtra("gender",object.getString("gender"));
                                            in.putExtra("mobileNo",object.getString("mobile_phone"));
                                            in.putExtra("birthday",object.getString("birthday"));
                                            in.putExtra("profileUrl",object.getString("public_profile"));
                                            startActivity(in);
                                            finish();
                                            String email = object.getString("email");
                                            Log.d("facebook",object.getString("id")+" : "+object.getString("name")+object.getString("public_profile"));
                                            Log.d("facebook",email);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String birthday = object.getString("birthday"); // 01/31/1980 format
                                            Log.d("facebook",birthday);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday,mobile_phone,public_profile");
                        request.setParameters(parameters);
                        request.executeAsync();

/*Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        Profile profile = Profile.getCurrentProfile();
                        Log.d("facebook : ",profile.getFirstName()+" : "+profile.getLastName()+" : "+profile.getId()+" : "+profile.getName()+" : "+profile.getLinkUri()+" : "+profile.getProfilePictureUri(300,300));
                        Toast.makeText(Test2.this, "Success", Toast.LENGTH_SHORT).show();
                   */
                    }
                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent in=new Intent(Test2.this,Register.class);
            in.putExtra("check","google");
            in.putExtra("name",account.getDisplayName());
            in.putExtra("email",account.getEmail());
            in.putExtra("id",account.getId());
            in.putExtra("profileUrl",account.getPhotoUrl());
            startActivity(in);
            finish();
            Toast.makeText(this, "sucess : "+account.getId()+" : "+account.getDisplayName()+" : "+account.getIdToken()+" : "+account.getEmail(), Toast.LENGTH_SHORT).show();
            Log.d("google ",account.getId()+" : "+account.getDisplayName()+" : "+account.getIdToken()+" : "+account.getEmail()+" : "+account.getFamilyName()+" : "+account.getGivenName()+" : "+account.getPhotoUrl());
            // Signed in successfully, show authenticated UI.
           // updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
