package com.sqlite.ativities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sqlite.AppLoader;
import com.sqlite.R;
import com.sqlite.adapter.MessageAdapter;
import com.sqlite.bean.MessageBean;
import com.sqlite.utils.DBManager;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<MessageBean> dataList=new ArrayList<>();

    private RecyclerView mRecyclerView=null;

    private EditText mEditText=null;

    private Button mSend=null;

    private MessageAdapter mMessageAdapter=null;

    private LinearLayoutManager mLinearLayoutManager;

    private DBManager mDBManager=null;

    private Random mRandom=null;    //随机数

    private int avatar[]=new int[]{R.mipmap.icon_man_25_01,R.mipmap.icon_man_25_02,R.mipmap.icon_man_25_03,R.mipmap.icon_man_25_04,R.mipmap.icon_man_35_01,R.mipmap.icon_man_35_02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initWidget();

        initAdapter();
    }

    /**
     * 初始化组件
     */
    private void initWidget(){
        mRecyclerView=(RecyclerView)super.findViewById(R.id.recycler_view);
        mEditText=(EditText)super.findViewById(R.id.edit_text);
        mSend=(Button) super.findViewById(R.id.send);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mSend.setOnClickListener(this);
    }


    /**
     * 初始化适配器
     */
    private void initAdapter(){
        mRandom=new Random();
        mMessageAdapter=new MessageAdapter(this,dataList);
        mRecyclerView.setAdapter(mMessageAdapter);
    }


    /**
     * 查询数据库，获取刚才插入的数据
     */
    private void initData(){
        mDBManager=new DBManager(AppLoader.getInstance());
        dataList.addAll(mDBManager.queryContent());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                //随机设置头像
                int number=mRandom.nextInt(avatar.length);
                mDBManager.insert(mEditText.getText().toString(),"");
                MessageBean bean=new MessageBean();
                bean.setAvatar(avatar[number]);
                bean.setContent(mEditText.getText().toString());
                bean.setName("");
                mMessageAdapter.addItem(bean);
                mEditText.clearFocus();
                mEditText.getText().clear();


                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.smoothScrollToPosition(mMessageAdapter.getItemCount());
                    }
                });
                mMessageAdapter.notifyDataSetChanged();
                break;
        }
    }
}
