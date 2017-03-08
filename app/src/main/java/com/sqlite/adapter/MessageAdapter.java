package com.sqlite.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sqlite.R;
import com.sqlite.bean.MessageBean;

import java.util.ArrayList;

/**
 * @author: xiaxueyi
 * @date: 2017-03-01
 * @time: 15:31
 * @说明:
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    private ArrayList<MessageBean> dataList=new ArrayList<>();

    public MessageAdapter(Context context,ArrayList<MessageBean> dataList) {
        this.mContext=context;
        this.dataList=dataList;

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.activity_main_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MessageBean bean=dataList.get(position);

        if(bean.getAvatar()==0){
            ((ViewHolder)viewHolder).avatar.setImageResource(R.mipmap.icon_man_25_01);
        }else{
            ((ViewHolder)viewHolder).avatar.setImageResource(bean.getAvatar());
        }
        ((ViewHolder)viewHolder).name.setText(bean.getName());
        ((ViewHolder)viewHolder).content.setText(bean.getContent());

    }


    private class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;
        private TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            avatar=(ImageView)itemView.findViewById(R.id.avatar);
            name=(TextView)itemView.findViewById(R.id.name);
            content=(TextView)itemView.findViewById(R.id.content);
        }
    }

    public void addItem(MessageBean bean){
        if(bean!=null){
            dataList.add(bean);
        }
    }


}

