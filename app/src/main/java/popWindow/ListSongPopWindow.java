package popWindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.List;
import adapter.ListSongAdapter;
import inc.shijj.musicer.R;
import util.Mp3Info;

/**
 * Created by shijj on 2016/1/29.
 */
public class ListSongPopWindow extends PopupWindow {

    private int mWidth;
    private int mHeight;
    private View mConvertView;
    private ListView mListView;
    private List<Mp3Info> mDatas;

    public ListSongPopWindow(Context context, List<Mp3Info> datas) {
        calWidthAndHeight(context);

        mConvertView = LayoutInflater.from(context).inflate(R.layout.popup, null);
        mDatas = datas;

        setContentView(mConvertView);

        setWidth(mWidth);
        setHeight(mHeight);

        /*设置可触摸*/
        setFocusable(true);
        setTouchable(true);

        /*外部可点击*/
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //点击外部使popwindow消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });


        initView(context);
        // initEvent();
    }

    private void initView(Context context) {

        mListView = (ListView) mConvertView.findViewById(R.id.item_song_name);
        mListView.setAdapter(new ListSongAdapter(context, mDatas));

    }

   /* private void initEvent() {
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.OnSelected(mDatas.get(position));//把数据传出去
                }

            }
        });

    }*/


    /**
     * 计算popupwindow的宽度和高度
     *
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

       /*赋值*/
        mWidth = outMetrics.widthPixels;
        mHeight = (int) (outMetrics.heightPixels * 0.4);

    }


}

