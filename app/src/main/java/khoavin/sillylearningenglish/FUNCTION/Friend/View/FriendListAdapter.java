package khoavin.sillylearningenglish.FUNCTION.Friend.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import khoavin.sillylearningenglish.SINGLE_OBJECT.Friend;
import khoavin.sillylearningenglish.PATTERN.RecycleViewAdapterPattern;
import khoavin.sillylearningenglish.R;
import khoavin.sillylearningenglish.SYSTEM.ToolFactory.ArrayConvert;


/**
 * Created by KhoaVin on 2/12/2017.
 */

public class FriendListAdapter extends RecycleViewAdapterPattern {

    public FriendListAdapter(Context mContext, ArrayList<Object> dataSource) {
        super(mContext, dataSource);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mLayoutInflater.inflate(R.layout.single_friend, parent, false);
        return new FriendListViewHolder(itemView);
    }
    public void UpdateDataSource(ArrayList<Object> friends){
        setDataSource(friends);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendListViewHolder mViewHolder = (FriendListViewHolder) holder;
        ArrayList<Friend> friends = ArrayConvert.toArrayList(getDataSource());
        Glide.with(getmContext())
                .load(friends.get(position).getAvatar())
                .into(mViewHolder.avatar);
        mViewHolder.singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(v.getContext(),"Popup : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();//showing popup menu
            }
        });
        mViewHolder.name.setText(friends.get(position).getName());
        if (friends.get(position).getOnline_Status()==true){
            mViewHolder.online_status.setVisibility(View.VISIBLE);
        }
        else if(friends.get(position).getOnline_Status()==false){
            mViewHolder.online_status.setVisibility(View.INVISIBLE);
        }
    }

}
