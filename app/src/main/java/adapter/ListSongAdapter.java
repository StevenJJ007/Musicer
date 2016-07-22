package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import inc.shijj.musicer.R;
import util.Mp3Info;

/**
 * Created by shijj on 2016/7/19.
 */
public class ListSongAdapter extends  ArrayAdapter<Mp3Info> {

        private LayoutInflater mInflater;
        private List<Mp3Info> mDatas;

        public ListSongAdapter(Context context, List<Mp3Info> objects) {
            super(context, 0, objects);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.song_list, parent, false);
                viewHolder.songName = (TextView) convertView.findViewById(R.id.song_list_name);
                viewHolder.songerName = (TextView) convertView.findViewById(R.id.songer_list_name);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Mp3Info bean = getItem(position);
            viewHolder.songName.setText(bean.getTitle()+"-");
            viewHolder.songerName.setText(bean.getArtist());
            return convertView;
        }

        private class ViewHolder {
            TextView songName;
            TextView songerName;
        }
    }

