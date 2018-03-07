package com.example.baidu.classmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> list = new ArrayList();
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mList = findViewById(R.id.list);
        Myadpater myadpater = new Myadpater(list, MainActivity.this);
        mList.setAdapter(myadpater);
    }

    public void initData() {
        for (int i = 0; i < 20; i++) {
            Person person = new Person("书籍使人进步" + i);
            list.add(person);
        }
    }

    public class Myadpater extends BaseAdapter {

        private ArrayList<Person> mPeople;
        private Context mContext;

        public Myadpater(ArrayList<Person> mPeople, Context mContext) {
            this.mPeople = mPeople;
            this.mContext = mContext;
        }

        @Override

        public int getCount() {
            return mPeople.size();
        }

        @Override
        public Object getItem(int position) {
            return mPeople.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView word = null;
            TextView delete = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent,false);

                word = convertView.findViewById(R.id.content);
                delete = convertView.findViewById(R.id.delete);
                ViewHolder holder = new ViewHolder(word, delete);
                convertView.setTag(holder);
            } else {
                ViewHolder holder = (ViewHolder) convertView.getTag();
                word = holder.word;
                delete = holder.delete;
            }
            word.setText(mPeople.get(position).getWord().toString());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPeople.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    public class ViewHolder {
        TextView word;
        TextView delete;

        public ViewHolder(TextView word, TextView delete) {
            this.word = word;
            this.delete = delete;
        }
    }
}
