package com.example.nikolayrudenko.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final ArrayList<Model> list = new ArrayList<Model>();
        list.add(new Model("First"));
        list.add(new Model("Second"));
        list.add(new Model("Third"));
        list.add(new Model("Fourth"));
        listView.setAdapter(new TestAdapter(this, list));

        findViewById(R.id.resultBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedItemPosition = listView.getCheckedItemPosition();
                Toast.makeText(MyActivity.this, String.format(
                                "Checked item %d with name %s", checkedItemPosition, list.get(checkedItemPosition).name),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    class TestAdapter extends BaseAdapter {
        private final LayoutInflater inflater;
        private final ArrayList<Model> list;

        TestAdapter(Context context, ArrayList<Model> list) {
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Model getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_list, null, false);
            }
            TextView nameView = (TextView) convertView.findViewById(R.id.name);
            nameView.setText(getItem(position).name);
            return convertView;
        }
    }

    class Model {
        public final String name;

        Model(String name) {
            this.name = name;
        }
    }
}
