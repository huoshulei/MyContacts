package edu.hsl.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import edu.hsl.mycontacts.adapter.MyAdapter;
import edu.hsl.mycontacts.shujuku.AssetsDBManager;
import edu.hsl.mycontacts.shujuku.DBRead;
import edu.hsl.mycontacts.zidingyi.TelClassInfo;

public class MyContacts extends AppCompatActivity {

    static List<TelClassInfo> listItemBean;
    MyAdapter adapter;
    ListView  listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);
        listView = (ListView) findViewById(R.id.lv_show);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectItem(position);
                adapter.notifyDataSetInvalidated();

                Intent intent = null;
                if (position==0) {
                    intent = new Intent(MyContacts.this, MyTel.class);
//                    intent.putExtra("idx",telClassInfo.idx);
                    intent.putExtra("idx",0);
//                    intent=new Intent();
//                    intent.setAction(Intent.ACTION_DIAL);
                } else {
//                    TelClassInfo telClassInfo=adapter.getItem(position);
                    intent = new Intent(MyContacts.this, MyTel.class);
//                    intent.putExtra("idx",telClassInfo.idx);
                    intent.putExtra("idx",listItemBean.get(position).idx);
                }
                startActivity(intent);
            }
        });
    }

    private void initAppDBFile() {
        if (!DBRead.isExistsTeladFile()) {
            AssetsDBManager.copyAssetsFileToFile(this,
                    "db/commonnum.db", DBRead.telFile);
        }
    }

    /*onResume 方法 系统自动调用*/
    protected void onResume() {
        super.onResume();
        initAppDBFile();
        DBRead.clear();
        try {
            listItemBean = DBRead.readTelClassList(this);
            if (listItemBean != null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter = new MyAdapter(this, listItemBean);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
