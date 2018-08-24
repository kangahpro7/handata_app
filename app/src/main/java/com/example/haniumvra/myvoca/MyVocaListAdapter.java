package com.example.haniumvra.myvoca;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haniumvra.R;
import com.example.haniumvra.db.DbController;
import com.example.haniumvra.utils.Utils;
import com.example.haniumvra.vo.VocaVo;

import java.util.ArrayList;
import java.util.List;

public class MyVocaListAdapter extends RecyclerView.Adapter<MyVocaListAdapter.MyVocaListViewHolder> {

    private Context context;
    private List<VocaVo> myVocaItemData = new ArrayList<VocaVo>();
    private int item_layout = 0;
    private View.OnClickListener urlListener;
    private View.OnClickListener delListener;

/* 리스트 형식일 때
    public MyVocaListAdapter(Context context, List<MyVocaItemData> myPageItemData, int item_layout) {
        this.context = context;
        this.myPageItemData = myPageItemData;
        this.item_layout = item_layout;
    }
*/

    public MyVocaListAdapter(Context context, View.OnClickListener delListener) {
        this.context = context;
        this.delListener = delListener;
    }

    public void setMyVocaItemData(List<VocaVo> myVocaItemData){
        this.myVocaItemData = myVocaItemData;
    }


    @Override
    public MyVocaListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_myvoca_child, parent, false);
        return new MyVocaListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyVocaListViewHolder holder, int position) {

        VocaVo item = myVocaItemData.get(position);

//        Glide.with(context).load(item.getVoca_img()).into(holder.listImageInfo);
        holder.listVoca.setText(item.getVoca());
        holder.listVocam.setText(item.getVoca_m());

    }

    @Override
    public int getItemCount() {
        return this.myVocaItemData.size();
    }

    public class MyVocaListViewHolder extends RecyclerView.ViewHolder {

        TextView listVoca;
        TextView listVocam;
        ImageView listImageInfo;



        public MyVocaListViewHolder(View itemView) {
            super(itemView);

            listVoca = (TextView) itemView.findViewById(R.id.item_voca);
            listVocam= (TextView) itemView.findViewById(R.id.item_voca_m);


        }
    }


}

