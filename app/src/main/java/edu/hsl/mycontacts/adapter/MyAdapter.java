package edu.hsl.mycontacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.hsl.mycontacts.R;
import edu.hsl.mycontacts.zidingyi.TelClassInfo;


/**
 * Created by Administrator on 2016/04/26.
 */
public class MyAdapter extends BaseAdapter {
    public List<TelClassInfo> listItem;
    LayoutInflater inflater;
    int            selectItem;
//    public static int tv_info;

    /*关联上下文,以及数据集合*/
    public MyAdapter(Context context, List<TelClassInfo> listItem) {
        this.listItem = listItem;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (listItem != null) {
            return listItem.size();
        }
        return 0;
    }

    @Override
    public TelClassInfo getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHaber haber;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dialog_adapter, null);
            haber = new ViewHaber();
            haber.tv_table = (TextView) convertView.findViewById(R.id.tv_table);
            convertView.setTag(haber);
        } else {
            haber = (ViewHaber) convertView.getTag();
        }
        haber.tv_table.setText(listItem.get(position).tv_table);
//        if (position == selectItem) {
//            tv_info = position;
//        }
        return convertView;


    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    class ViewHaber {
        TextView  tv_table;
        TextView  tv_name;
        TextView  tv_tel;
        ImageView im_logo;
    }
}
