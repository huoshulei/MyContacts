package edu.hsl.mycontacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.hsl.mycontacts.adapter.MyAdapter;
import edu.hsl.mycontacts.adapter.MyAdapterTel;
import edu.hsl.mycontacts.shujuku.AssetsDBManager;
import edu.hsl.mycontacts.shujuku.DBRead;
import edu.hsl.mycontacts.zidingyi.TelClassInfo;

/**
 *
 */
public class MyTel extends AppCompatActivity {
    ArrayList<TelClassInfo> numberTel;
    int                     idx;
    MyAdapter               adapter;
    ListView                listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tel);
        idx = getIntent().getIntExtra("idx", -1);
        listView = (ListView) findViewById(R.id.lv_showTel);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name   = numberTel.get(position).tv_name;
                String telNum = numberTel.get(position).tv_tel;
                showCall(name, telNum);
            }
        });
    }

    public void showCall(final String name, final String telNum) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告!");
        builder.setMessage("是否给" + name + "拨打电话?\t\t号码:" + telNum);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("拨号", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telNum));
                startActivity(intent);
            }
        });
        builder.show();
    }

    private void initAppDBFile() {
        if (!DBRead.isExistsTeladFile()) {
            AssetsDBManager.copyAssetsFileToFile(this,
                    "db/commonnum.db", DBRead.telFile);
        }
    }

    /**
     * onResume 方法 系统自动调用
     */
    protected void onResume() {
        super.onResume();
        initAppDBFile();
        DBRead.clear();
        if (idx == 0) {
            try {
                numberTel=DBRead.readTelbendi(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                numberTel = DBRead.readTelClassList(idx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        adapter = new MyAdapterTel(this, numberTel);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}