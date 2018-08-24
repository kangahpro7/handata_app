package com.example.haniumvra.rank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haniumvra.R;
import com.example.haniumvra.utils.Utils;
import com.example.haniumvra.vo.RankDataVo;


import java.util.ArrayList;
import java.util.List;

public class RankListAdapter extends RecyclerView.Adapter<RankListAdapter.RankListViewHolder> {

    private Context context;
    private List<RankDataVo> rankItemData = new ArrayList<RankDataVo>();
    private int item_layout = 0;

/* 리스트 형식일 때
    public MyVocaListAdapter(Context context, List<MyVocaItemData> myPageItemData, int item_layout) {
        this.context = context;
        this.myPageItemData = myPageItemData;
        this.item_layout = item_layout;
    }
*/

    public RankListAdapter(Context context) {
        this.context = context;

    }

    public void setRankItemData(List<RankDataVo> rankItemData){
        this.rankItemData = rankItemData;
    }


    @Override
    public RankListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rank_child, parent, false);
        return new RankListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RankListViewHolder holder, int position) {

        RankDataVo item = rankItemData.get(position);

        holder.listUserId.setText(item.getUser_id());
        holder.listUserScore.setText(Utils.moneyFormatToWon(item.getUser_score())+"점");
 //       holder.listDrama.setText("#" + item.getD_name());


    }

    @Override
    public int getItemCount() {
        return this.rankItemData.size();
    }

    public class RankListViewHolder extends RecyclerView.ViewHolder {

        TextView listUserId;
        TextView listUserScore;

        public RankListViewHolder(View itemView) {
            super(itemView);

            listUserId = (TextView) itemView.findViewById(R.id.item_id);
            listUserScore = (TextView) itemView.findViewById(R.id.item_score);

        }
    }


}

