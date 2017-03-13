package khoavin.sillylearningenglish.NetworkService.Implementation;


import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import khoavin.sillylearningenglish.EventListener.SingleEvent.SendMessageListener;
import khoavin.sillylearningenglish.FirebaseObject.FirebaseChat;
import khoavin.sillylearningenglish.FirebaseObject.FirebaseConstant;
import khoavin.sillylearningenglish.NetworkService.Interfaces.IChatService;

public class ChatService implements IChatService{
    final String TAG = "Chat Service";
    private DatabaseReference ChatRef = FirebaseDatabase.getInstance().getReference().child(FirebaseConstant.ARG_CHAT_ROOMS);
    private FirebaseUser Current_User = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void sendMessageToUid(String senderUid, String receiverUid, String message, SendMessageListener sendMessageListener){

        FirebaseChat firebaseChat = new FirebaseChat(message,false);
        ChatRef.child(receiverUid).child(senderUid).push().setValue(firebaseChat);
    }

    @Override
    public void getNewMessage(){
        ChatRef.child(Current_User.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG,dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}