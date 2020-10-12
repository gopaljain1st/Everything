package com.example.testnutrition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
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
import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity
{
    private static final String TAG="FACELOG";
    private static final int RC_SIGN_IN =0 ;
    Button signInWithMobile,signInWithGoogle,signInWithFacebook;
    TextView newUser;
    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    String id;
    SharedPreferences sp=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_button_activity);
        initComponent();
       newUser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(WelcomeActivity.this,Test2.class));
               finish();
           }
       });
       signInWithMobile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(WelcomeActivity.this,MobileLoginactivity.class));
               finish();
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
                LoginManager.getInstance().logInWithReadPermissions(WelcomeActivity.this, Arrays.asList("public_profile","email","mobile_phone"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(final JSONObject object, GraphResponse response)
                                    {
                                        try {
                                            id=object.getString("id");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        final ProgressDialog pd = new ProgressDialog(WelcomeActivity.this);
                                        pd.setTitle("Nutritang");
                                        pd.setMessage("Loading...");
                                        pd.show();
                                        String url = "https://relishking.com/restrauntapp/checkById.php";
                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response)
                                            {
                                                if(response.equals("Wrong Id"))
                                                    Toast.makeText(WelcomeActivity.this, "Not Register", Toast.LENGTH_SHORT).show();
                                                else
                                                {
                                                    Toast.makeText(WelcomeActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                                                    Intent in = new Intent(WelcomeActivity.this, MainActivity.class);
                                                    String arr[] = response.split(",");
                                                    SharedPreferences.Editor editor=sp.edit();
                                                    Log.d("respone : ",response);
                                                    editor.putString("id",id);
                                                    editor.putString("name",arr[0]);
                                                    editor.putString("email",arr[1]);
                                                    editor.putString("mobile",arr[2]);
                                                    editor.putString("address",arr[3]);
                                                    editor.putString("height",arr[4]);
                                                    editor.putString("weight",arr[5]);
                                                    editor.putString("occupation",arr[6]);
                                                    editor.putString("gender",arr[7]);
                                                    String s="https://relishking.com/restrauntapp/images/";
                                                    editor.putString("profileUrl",s+(arr[8].trim()));
                                                    editor.putString("password",arr[9]);
                                                    editor.putString("age",arr[10]);
                                                    editor.commit();
                                                    startActivity(in);
                                                    finish();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                pd.dismiss();
                                                Toast.makeText(WelcomeActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                                            }
                                        }) {
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> map = new HashMap<>();
                                                map.put("id",id);
                                                return map;
                                            }
                                        };
                                        RequestQueue requestQueue = Volley.newRequestQueue(WelcomeActivity.this, new HurlStack());
                                        requestQueue.add(stringRequest);
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday,mobile_phone,public_profile");
                        request.setParameters(parameters);
                        request.executeAsync();
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
            id=account.getId();
            final ProgressDialog pd = new ProgressDialog(WelcomeActivity.this);
            pd.setTitle("Nutritang");
            pd.setMessage("Loading...");
            pd.show();
            String url = "https://relishking.com/restrauntapp/checkById.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {
                    pd.dismiss();
                    if(response.equals("Wrong Id"))
                    {
                        Toast.makeText(WelcomeActivity.this, "Not Register", Toast.LENGTH_SHORT).show();
                        Log.d("id",id);
                    }
                    else
                    {
                        Toast.makeText(WelcomeActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(WelcomeActivity.this, MainActivity.class);
                        String arr[] = response.split(",");
                        SharedPreferences.Editor editor=sp.edit();
                        Log.d("respone : ",response);
                        editor.putString("id",id);
                        editor.putString("name",arr[0]);
                        editor.putString("email",arr[1]);
                        editor.putString("mobile",arr[2]);
                        editor.putString("address",arr[3]);
                        editor.putString("height",arr[4]);
                        editor.putString("weight",arr[5]);
                        editor.putString("occupation",arr[6]);
                         editor.putString("gender",arr[7]);
                        String s="https://relishking.com/restrauntapp/images/";
                        editor.putString("profileUrl",s+(arr[8].trim()));
                        editor.putString("password",arr[9]);
                        editor.putString("age",arr[10]);
                        editor.commit();
                        startActivity(in);
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pd.dismiss();
                    Toast.makeText(WelcomeActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("id",id);
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(WelcomeActivity.this, new HurlStack());
            requestQueue.add(stringRequest);
        } catch (ApiException e) {
             Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String s=sp.getString("id","0");
        if(!s.equals("0"))
        {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            finish();
        }
    }

    void initComponent()
    {
        signInWithFacebook=findViewById(R.id.signInWithFacebook);
        signInWithGoogle=findViewById(R.id.signInWithGoogle);
        signInWithMobile=findViewById(R.id.signInWithMobile);
        newUser=findViewById(R.id.tvRegister);
        sp=getSharedPreferences("customer",MODE_PRIVATE);
    }
}
