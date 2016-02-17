package myapplication.bits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 11/30/2015.
 */

public class MyArrayAdapter extends ArrayAdapter<MyMessage> {

    ArrayList<MyMessage> msgList;
    protected TextView tvSubject;
    protected TextView tvSender;
    protected TextView tvTime;

    public MyArrayAdapter(Context context, ArrayList<MyMessage> mList) {

        super(context, -1, mList);
        msgList = mList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_layout, null);
        }
       tvSubject = (TextView) v.findViewById(R.id.tvSubject);
       tvSender = (TextView) v.findViewById(R.id.tvSender);
       tvTime = (TextView) v.findViewById(R.id.tvTime);

        MyMessage msg = (MyMessage)msgList.get(position);
        tvSubject.setText(msg.subject);
        tvSender.setText(msg.sender);
        tvTime.setText(msg.time);
        return v;
    }
}


