package com.example.shengsheng.uibestpractice;

import android.support.v7.app.AlertController.RecycleListView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg>msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecycleListView.msgRecycleView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputText=(EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send);
        msgRecyclerView=(RecycleListView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String content=inputText.getText().toString();
                if(!"".equal(content))
                {
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    //当有新消息时刷新ListView中显示的内容
                    msgRecyclerView.sorollToPosition(msgList.size()-1);
                    //将ListView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });
    }
    private void initMsg()
    {
        Msg msg1=new Msg("hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("hello,who is that",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("this is Tom",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
