package com.yhbc.base.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yhbc.base.R;
import com.yhbc.base.interfaces.NotifyT;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class CommonDropDown implements View.OnClickListener {
    private Context mContext;
    private NotifyT<Integer> t;
    private PopupWindow window;
    private RecyclerView view;
    private final FrameLayout inflate;
    private boolean firstShow = true;

    public CommonDropDown(Context mContext,NotifyT<Integer> t){
        this.mContext = mContext;
        this.t = t;
        inflate = (FrameLayout) View.inflate(mContext, R.layout.common_drop_down_dialog, null);
        view = (RecyclerView) inflate.findViewById(R.id.recycle_view);
        view.setLayoutManager(new LinearLayoutManager(mContext));
        view.setAdapter(new DropDownAdapter(null));
        window = new PopupWindow(inflate, -1, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
    }

    public PopupWindow showBelow(View target, final List<String> list) {
        ((DropDownAdapter)view.getAdapter()).changeData(list);
        window.setWidth(target.getWidth());
        if(firstShow)
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    int i = bottom - top;
                    if(i >0){
                        firstShow = false;
                        int dimension = (int) CommonDropDown.this.mContext.getResources().getDimension(R.dimen.x548);
                        ViewGroup.LayoutParams params = view.getLayoutParams();
                        if(dimension<i){
                            params.height = dimension;
                        }else{
                            params.height = i;
                        }
                        view.setLayoutParams(params);
                        view.removeOnLayoutChangeListener(this);
                    }
                }
            });
        window.showAsDropDown(target);
        return window;
    }

    public void notifyDataChanged(List<String> newData){
        RecyclerView.Adapter adapter = view.getAdapter();
        if(adapter !=null){
            ((DropDownAdapter)adapter).changeData(newData);
        }
    }

    public void destroy(){
        if(window!=null){
            window.dismiss();
            window.setOnDismissListener(null);
            window=null;
        }
        mContext=null;
        t = null;
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        int pos = (int) tv.getTag();
        if(t!=null){
            t.notify(pos);
            if(window!=null)
                window.dismiss();
        }
    }

    public class DropDownAdapter extends RecyclerView.Adapter<DropDownItemHolder>{

        List<String> data;
        final int color1 = mContext.getResources().getColor(R.color.color_332410);
        final float size = mContext.getResources().getDimension(R.dimen.x34);
        final int paddingLeft = (int) mContext.getResources().getDimension(R.dimen.x20);
        final int height = (int) mContext.getResources().getDimension(R.dimen.x88);
        public DropDownAdapter(List<String> data){
            this.data = data;

        }
        @Override
        public DropDownItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(mContext);
            tv.setTextColor(color1);
            tv.setTextSize(size);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setPadding(paddingLeft,0,0,0);
            tv.setBackgroundResource(R.drawable.bg_drop_down_selected);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(-1,height);
            tv.setLayoutParams(params);
            tv.setOnClickListener(CommonDropDown.this);
            DropDownItemHolder holder = new DropDownItemHolder(tv);
            return holder;
        }

        public void changeData(List<String> data){
            if(data==this.data)
                return;
            this.data = data;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(DropDownItemHolder holder, int position) {
            String str = data.get(position);
            ((TextView)holder.itemView).setText(str);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return data==null?0:data.size();
        }
    }

    public static class DropDownItemHolder extends RecyclerView.ViewHolder{

        public DropDownItemHolder(View itemView) {
            super(itemView);
        }
    }
}
