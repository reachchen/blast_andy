package com.yhbc.base.common.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yhbc.base.R;


/**
 * 日期
 * Created by xhj on 2018/3/15.
 */
public class DatePickAdapter extends RecyclerView.Adapter<DatePickAdapter.DateHolder> {
    private String[] datas;
    private LayoutInflater mInflater;
    private int selectNum;
    private Context context;

    public DatePickAdapter(String[] datas, Context context) {
        this.datas = datas;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void refresh(String[] datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public DateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DateHolder holder = new DateHolder(LayoutInflater.from(
                context).inflate(R.layout.base_item_date_tv, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final DateHolder holder, int position) {
        holder.tv.setText(datas[position]);
        if ((selectNum + "").equals(datas[position])) {
            holder.tv.setBackgroundColor(context.getResources().getColor(R.color.color_fa6a28));
            holder.tv.setTextColor(context.getResources().getColor(R.color.color_fffdf7));
        } else {
            holder.tv.setBackgroundColor(context.getResources().getColor(R.color.color_f0ece1));
            holder.tv.setTextColor(context.getResources().getColor(R.color.color_5f5245));
        }
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getItem(int position) {
        return datas[position];
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.length;
    }

    /**
     * 选中项的值
     *
     * @param selectNum num
     */
    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    class DateHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public DateHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }


}
