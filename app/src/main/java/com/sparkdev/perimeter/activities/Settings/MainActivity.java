package com.sparkdev.perimeter.activities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomsCompletionListener;
//import com.sparkdev.perimeter.activities.Firebase.GetChatRoomsCompletionListener;
//import com.sparkdev.perimeter.activities.Firebase.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.FirebaseAPI2;
import com.sparkdev.perimeter.models.Message;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnShowSetting;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowSetting = (Button) findViewById(R.id.button);

        btnShowSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, Settings_Activity.class));

            }

        });

        FirebaseAPI2.getInstance(this).getAllChatRooms(new GetChatRoomsCompletionListener() {
            @Override
            public void onSuccess(List<ChatRoom> chatRooms) {
                ChatRoom chatRoom1 = chatRooms.get(0);

                FirebaseAPI2.getInstance(MainActivity.this).getMessagesForChatRoom(chatRoom1, new GetChatRoomMessagesCompletionListener() {
                    @Override
                    public void onSuccess(List<Message> messages) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });

            }

            @Override
            public void onFailure() {

            }
        });

    }
}
