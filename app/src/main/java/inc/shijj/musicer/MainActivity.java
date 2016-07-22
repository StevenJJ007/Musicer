package inc.shijj.musicer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.List;
import util.FindSongs;
import popWindow.ListSongPopWindow;
import util.Mp3Info;

public class MainActivity extends AppCompatActivity {

    private TextView musicer_name;
    private TextView musicer_songer;
    private ImageView musicer_start;
    private ImageButton musicer_list_song;
    private ImageView musicer_pic;
    private FindSongs finder;
    private List<Mp3Info> mp3Infos;
    private ListSongPopWindow mListSongDirPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finder = new FindSongs();
        mp3Infos = finder.getMp3Infos(MainActivity.this.getContentResolver());
        mListSongDirPopWindow = new ListSongPopWindow(this, mp3Infos);

        initViews();
        initEvents();
    }

    private void initViews() {
        musicer_songer = (TextView) findViewById(R.id.musicer_songer);
        musicer_name = (TextView) findViewById(R.id.musicer_name);
        musicer_pic = (ImageView) findViewById(R.id.musicer_pic);
        musicer_start = (ImageView) findViewById(R.id.musicer_start);
        musicer_list_song = (ImageButton) findViewById(R.id.musicer_list_song);

    }

    private void initEvents() {
        musicer_list_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListSongDirPopWindow.setAnimationStyle(R.style.dir_popwindow_anim);
                mListSongDirPopWindow.showAsDropDown(musicer_list_song, 0, 0);
                lightOff();
            }
        });

        mListSongDirPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
    }


    /**
     * 内容区域变量
     */
    private void lightOn() {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }


    /**
     * 内容区域变暗
     */
    private void lightOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);

    }
}
