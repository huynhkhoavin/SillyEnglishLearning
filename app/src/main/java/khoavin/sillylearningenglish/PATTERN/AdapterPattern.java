package khoavin.sillylearningenglish.PATTERN;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by KhoaVin on 1/19/2017.
 */

public abstract class AdapterPattern extends RecyclerView.Adapter {
    protected LayoutInflater mLayoutInflater;
    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public Object[] getDataSource() {
        return dataSource;
    }

    public void setDataSource(Object[] dataSource) {
        this.dataSource = dataSource;
    }

    private Context mContext;
    private Object[] dataSource;

    public AdapterPattern(Context mContext,Object[] dataSource){
        this.mContext = mContext;
        this.dataSource = dataSource;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}
