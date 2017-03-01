package khoavin.sillylearningenglish.NetworkService.Implementation;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import khoavin.sillylearningenglish.FUNCTION.Authentication.Login.OnLoginListener;
import khoavin.sillylearningenglish.FirebaseObject.FirebaseAccount;
import khoavin.sillylearningenglish.NetworkService.Interfaces.IAuthenticationService;
import khoavin.sillylearningenglish.R;

/**
 * Created by KhoaVin on 2/21/2017.
 */

public class AuthenticationService implements IAuthenticationService {
    private static final int RC_SIGN_IN = 1;
    private static final int RC_LOG_IN = 2;
    private static final String TAG = "Authentication Service";
    private OnLoginListener mOnLoginListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAccount firebaseAccount;
    private DatabaseReference onlineRef;
    private DatabaseReference currentUserRef;
    private DatabaseReference onlineViewersCountRef;
    private int Login_Count = 1;
    private void initOnlineRef(){}
    @Override
    public void FirebaseAuthInit(final Activity activity) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                com.google.firebase.auth.FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    LoginSuccess(activity);
                } else {
                    LoginFail(activity);
                }
            }
        };
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void initOnlineCheck(String uid){
        onlineRef = mDatabaseReference.child(".info/connected");
        currentUserRef = mDatabaseReference.child("/presence/"+uid);
        onlineViewersCountRef = mDatabaseReference.child("/presence");
        onlineViewersCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "DataSnapshot:" + dataSnapshot);
                Log.e(TAG, String.valueOf(dataSnapshot.getChildrenCount()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "DatabaseError:" + databaseError);
            }
        });
        onlineRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "DataSnapshot:" + dataSnapshot);
                if (dataSnapshot.getValue(Boolean.class)){
                    currentUserRef.onDisconnect().removeValue();

                    Log.e(TAG,"Is Online");
                    currentUserRef.setValue(true);
                }
                else
                {
                    currentUserRef.setValue(false);
                    Log.e(TAG, "Is Offline");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "DatabaseError:" + databaseError);
            }
        });
    }
    @Override
    public void FirebaseAuthAttach() {
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void FirebaseAuthDetach() {
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            com.google.firebase.auth.FirebaseUser user = mFirebaseAuth.getCurrentUser();
            Log.e(TAG,user.getPhotoUrl().toString());
            if (resultCode == ResultCodes.OK) {

                //Log the Firebase Username
                Log.e(TAG,mFirebaseAuth.getCurrentUser().getDisplayName());
                Log.e(TAG,mFirebaseAuth.getCurrentUser().getUid());

                // Init new FirebaseAccount
                firebaseAccount = new FirebaseAccount(user.getUid(),user.getEmail(),user.getToken(true).toString(),user.getDisplayName(),user.getPhotoUrl().toString());
                //Write new User on FirebaseDatabase
                mFirebaseDatabase.getReference().child("users").child(firebaseAccount.getUid()).setValue(firebaseAccount);
                mFirebaseDatabase.getReference().child("friends").child(firebaseAccount.getUid()).child(firebaseAccount.getUid()).setValue(firebaseAccount);
                //Init Presence System
                initOnlineCheck(firebaseAccount.getUid());
                return;
            }
            else if(requestCode == RC_LOG_IN) {
                Log.e(TAG,"String Extra: " + data.getStringExtra("uid"));
            }
            else{
                // Sign in failed
                if (response == null) {
                    // User pressed back button

                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {

                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    return;
                }
            }

        }
    }
    @Override
    public void LoginSuccess(Activity activity) {
        com.google.firebase.auth.FirebaseUser user = mFirebaseAuth.getCurrentUser();
        user.getToken(true);
        if (Login_Count==1){
            initOnlineCheck(user.getUid());
            Login_Count = 0;
        }

    }
    @Override
    public void LoginFail(Activity activity) {
        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.FirebaseLoginTheme)
                        .setProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void Logout(Activity activity) {
        currentUserRef.removeValue();
        currentUserRef.setValue(false);
        AuthUI.getInstance().signOut(activity);
    }

    @Override
    public void AddOnlineChecking(Activity activity) {
        mFirebaseAuth.getCurrentUser().getUid();
    }
}
