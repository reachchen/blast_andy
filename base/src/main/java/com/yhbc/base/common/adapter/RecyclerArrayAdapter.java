package com.yhbc.base.common.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract public class RecyclerArrayAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    /**
     * Contains the list of objects that represent the data of this ArrayAdapter.
     * The content of this list is referred to as "the array" in the documentation.
     */
    protected List<T> mObjects;

    protected ArrayList<ItemView> headers = new ArrayList<>();
    protected ArrayList<ItemView> footers = new ArrayList<>();

    protected OnItemClickListener mItemClickListener;
    protected OnItemLongClickListener mItemLongClickListener;

    RecyclerView.AdapterDataObserver mObserver;
    private RecyclerArrayAdapter.OnRecyclerViewItemClickListener mOnItemClickListener;

    public interface ItemView {
        View onCreateView(ViewGroup parent);

        void onBindView(View headerView);
    }

    public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        private int mMaxCount;

        public GridSpanSizeLookup(int maxCount) {
            this.mMaxCount = maxCount;
        }

        @Override
        public int getSpanSize(int position) {
            if (headers.size() != 0) {
                if (position < headers.size()) return mMaxCount;
            }
            if (footers.size() != 0) {
                int i = position - headers.size() - mObjects.size();
                if (i >= 0) {
                    return mMaxCount;
                }
            }
            return 1;
        }
    }

    public GridSpanSizeLookup obtainGridSpanSizeLookUp(int maxCount) {
        return new GridSpanSizeLookup(maxCount);
    }

    /**
     * Lock used to modify the content of {@link #mObjects}. Any write operation
     * performed on the array should be synchronized on this lock.
     */
    private final Object mLock = new Object();


    /**
     * Indicates whether or not {@link #notifyDataSetChanged()} must be called whenever
     * {@link #mObjects} is modified.
     */
    private boolean mNotifyOnChange = true;

    private Context mContext;


    /**
     * Constructor
     *
     * @param context The current context.
     */
    public RecyclerArrayAdapter(Context context) {
        init(context, new ArrayList());
    }


    /**
     * Constructor
     *
     * @param context The current context.
     * @param objects The objects to represent in the ListView.
     */
    public RecyclerArrayAdapter(Context context, T[] objects) {
        init(context, Arrays.asList(objects));
    }

    /**
     * Constructor
     *
     * @param context The current context.
     * @param objects The objects to represent in the ListView.
     */
    public RecyclerArrayAdapter(Context context, List<T> objects) {
        init(context, objects);
    }


    private void init(Context context, List<T> objects) {
        mContext = context;
        mObjects = objects;
    }


    public void addHeader(ItemView view) {
        if (view == null) throw new NullPointerException("ItemView can't be null");
        headers.add(view);
        notifyItemInserted(footers.size() - 1);
    }

    public void addFooter(ItemView view) {
        if (view == null) throw new NullPointerException("ItemView can't be null");
        footers.add(view);
        notifyItemInserted(headers.size() + getCount() + footers.size() - 1);
    }

    public void removeAllHeader() {
        int count = headers.size();
        headers.clear();
        notifyItemRangeRemoved(0, count);
    }

    public void removeAllFooter() {
        int count = footers.size();
        footers.clear();
        notifyItemRangeRemoved(headers.size() + getCount(), count);
    }

    public ItemView getHeader(int index) {
        return headers.get(index);
    }

    public ItemView getFooter(int index) {
        return footers.get(index);
    }

    public int getHeaderCount() {
        return headers.size();
    }

    public int getFooterCount() {
        return footers.size();
    }

    public void removeHeader(ItemView view) {
        int position = headers.indexOf(view);
        headers.remove(view);
        notifyItemRemoved(position);
    }

    public void removeFooter(ItemView view) {
        int position = headers.size() + getCount() + footers.indexOf(view);
        footers.remove(view);
        notifyItemRemoved(position);
    }


    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);

    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    public void add(T object) {
        if (object != null) {
            synchronized (mLock) {
                mObjects.add(object);
            }
        }
        if (mObserver != null) mObserver.onItemRangeInserted(getCount() + 1, 1);
        if (mNotifyOnChange) notifyItemInserted(headers.size() + getCount() + 1);
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    public void addAll(Collection<? extends T> collection) {

        if (collection != null && collection.size() != 0) {
            synchronized (mLock) {
                mObjects.addAll(collection);
            }
        }
        int dataCount = collection == null ? 0 : collection.size();
        if (mObserver != null) mObserver.onItemRangeInserted(getCount() - dataCount + 1, dataCount);
        if (mNotifyOnChange)
            notifyItemRangeInserted(headers.size() + getCount() - dataCount + 1, dataCount);

    }

    public void setmObjects(List<T> mObjects) {
        this.mObjects = mObjects;
        notifyDataSetChanged();
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    public void addAll(T[] items) {
        if (items != null && items.length != 0) {
            synchronized (mLock) {
                Collections.addAll(mObjects, items);
            }
        }
        int dataCount = items == null ? 0 : items.length;
        if (mObserver != null) mObserver.onItemRangeInserted(getCount() - dataCount + 1, dataCount);
        if (mNotifyOnChange)
            notifyItemRangeInserted(headers.size() + getCount() - dataCount + 1, dataCount);
    }

    /**
     * 插入，不会触发任何事情
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    public void insert(T object, int index) {
        synchronized (mLock) {
            mObjects.add(index, object);
        }
        if (mObserver != null) mObserver.onItemRangeInserted(index, 1);
        if (mNotifyOnChange) notifyItemInserted(headers.size() + index + 1);
    }

    /**
     * 插入数组，不会触发任何事情
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    public void insertAll(T[] object, int index) {
        synchronized (mLock) {
            mObjects.addAll(index, Arrays.asList(object));
        }
        int dataCount = object == null ? 0 : object.length;
        if (mObserver != null) mObserver.onItemRangeInserted(index + 1, dataCount);
        if (mNotifyOnChange) notifyItemRangeInserted(headers.size() + index + 1, dataCount);
    }

    /**
     * 插入数组，不会触发任何事情
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    public void insertAll(Collection<? extends T> object, int index) {
        synchronized (mLock) {
            mObjects.addAll(index, object);
        }
        int dataCount = object == null ? 0 : object.size();
        if (mObserver != null) mObserver.onItemRangeInserted(index + 1, dataCount);
        if (mNotifyOnChange) notifyItemRangeInserted(headers.size() + index + 1, dataCount);
    }

    /**
     * 删除，不会触发任何事情
     *
     * @param object The object to remove.
     */
    public void remove(T object) {
        int position = mObjects.indexOf(object);
        synchronized (mLock) {
            if (mObjects.remove(object)) {
                if (mObserver != null) mObserver.onItemRangeRemoved(position, 1);
                if (mNotifyOnChange) notifyItemRemoved(headers.size() + position);
            }
        }
    }

    /**
     * 删除，不会触发任何事情
     *
     * @param position The position of the object to remove.
     */
    public void remove(int position) {
        synchronized (mLock) {
            mObjects.remove(position);
        }
        if (mObserver != null) mObserver.onItemRangeRemoved(position, 1);
        if (mNotifyOnChange) notifyItemRemoved(headers.size() + position);
    }


    /**
     * 触发清空
     */
    public void clear() {
        int count = mObjects.size();
        synchronized (mLock) {
            mObjects.clear();
        }
        if (mObserver != null) mObserver.onItemRangeRemoved(0, count);
        if (mNotifyOnChange) notifyItemRangeRemoved(headers.size(), count);
    }

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained
     *                   in this adapter.
     */
    public void sort(Comparator<? super T> comparator) {
        synchronized (mLock) {
            Collections.sort(mObjects, comparator);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }


    /**
     * Control whether methods that change the list ({@link #add},
     * {@link #insert}, {@link #remove}, {@link #clear}) automatically call
     * {@link #notifyDataSetChanged}.  If set to false, caller must
     * manually call notifyDataSetChanged() to have the changes
     * reflected in the attached view.
     * <p>
     * The default is true, and calling notifyDataSetChanged()
     * resets the flag to true.
     *
     * @param notifyOnChange if true, modifications to the list will
     *                       automatically call {@link
     *                       #notifyDataSetChanged}
     */
    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }


    /**
     * Returns the context associated with this array adapter. The context is used
     * to create views from the resource passed to the constructor.
     *
     * @return The Context associated with this adapter.
     */
    public Context getContext() {
        return mContext;
    }

    public void setContext(Context ctx) {
        mContext = ctx;
    }

    /**
     * 这个函数包含了头部和尾部view的个数，不是真正的item个数。
     *
     * @return
     */
    @Deprecated
    @Override
    public final int getItemCount() {
        if (mObjects != null && mObjects.size() > 0) {
            return mObjects.size() + headers.size() + footers.size();
        } else {
            return headers.size() + footers.size();
        }
    }

    /**
     * 应该使用这个获取item个数
     *
     * @return
     */
    public int getCount() {
        if (mObjects != null && mObjects.size() > 0) {
            return mObjects.size();
        } else {
            return 0;
        }
    }

    private View createSpViewByType(ViewGroup parent, int viewType) {
        for (ItemView headerView : headers) {
            if (headerView.hashCode() == viewType) {
                View view = headerView.onCreateView(parent);
                StaggeredGridLayoutManager.LayoutParams layoutParams;
                if (view.getLayoutParams() != null)
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(view.getLayoutParams());
                else
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(true);
                view.setLayoutParams(layoutParams);
                return view;
            }
        }
        for (ItemView footerview : footers) {
            if (footerview.hashCode() == viewType) {
                View view = footerview.onCreateView(parent);
                StaggeredGridLayoutManager.LayoutParams layoutParams;
                if (view.getLayoutParams() != null)
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(view.getLayoutParams());
                else
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(true);
                view.setLayoutParams(layoutParams);
                return view;
            }
        }
        return null;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = createSpViewByType(parent, viewType);
        if (view != null) {
            return new StateViewHolder(view);
        }

        final BaseViewHolder viewHolder = OnCreateViewHolder(parent, viewType);

        //itemView 的点击事件
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(viewHolder.getLayoutPosition() - headers.size());
                }
            });
        }
        //itemView 的点击事件
        viewHolder.setOnItemClickListener(new BaseViewHolder.OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                if (mOnItemClickListener != null && mObjects.size() > 0) {
                    mOnItemClickListener.onItemClick(view, viewType, mObjects.get(viewHolder.getAdapterPosition() - headers.size()), viewHolder.getAdapterPosition() - headers.size());
                }
            }
        });

        if (mItemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemLongClickListener.onItemLongClick(viewHolder.getAdapterPosition() - headers.size());
                }
            });
        }
        return viewHolder;
    }

    abstract public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType);


    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setId(position);
        if (headers.size() != 0 && position < headers.size()) {
            headers.get(position).onBindView(holder.itemView);
            return;
        }

        int i = position - headers.size() - mObjects.size();
        if (footers.size() != 0 && i >= 0) {
            footers.get(i).onBindView(holder.itemView);
            return;
        }
        OnBindViewHolder(holder, position - headers.size());
    }


    public void OnBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setData(getItem(position), position);
    }


    @Deprecated
    @Override
    public final int getItemViewType(int position) {
        if (headers.size() != 0) {
            if (position < headers.size()) return headers.get(position).hashCode();
        }
        if (footers.size() != 0) {
            /*
            eg:
            0:header1
            1:header2   2
            2:object1
            3:object2
            4:object3
            5:object4
            6:footer1   6(position) - 2 - 4 = 0
            7:footer2
             */
            int i = position - headers.size() - mObjects.size();
            if (i >= 0) {
                return footers.get(i).hashCode();
            }
        }
        return getViewType(position - headers.size());
    }

    public int getViewType(int position) {
        return 0;
    }


    public List<T> getAllData() {
        return new ArrayList<>(mObjects);
    }

    /**
     * {@inheritDoc}
     */
    public T getItem(int position) {
        return mObjects.get(position);
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    public int getPosition(T item) {
        return mObjects.indexOf(item);
    }

    /**
     * {@inheritDoc}
     */
    public long getItemId(int position) {
        return position;
    }

    private class StateViewHolder extends BaseViewHolder {

        public StateViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view, int viewType, T data, int position);
    }

    public void setOnItemClickListener(RecyclerArrayAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
