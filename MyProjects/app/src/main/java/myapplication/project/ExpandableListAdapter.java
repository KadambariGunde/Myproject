package myapplication.project;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import myapplication.bits.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);


        //if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (childPosition == 0) {
                convertView = infalInflater.inflate(R.layout.table_child_items, null);
                final EditText etChildItem = (EditText) convertView.findViewById(R.id.etChildItem);
                etChildItem.setText(childText);
                etChildItem.requestFocus();
                etChildItem.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                            ChangedItemValues(groupPosition,childPosition,s.toString());
                    }
                });

            } else {
                convertView = infalInflater.inflate(R.layout.table_child, null);
                TextView txtListChild = (TextView) convertView.findViewById(R.id.tvChild);
                txtListChild.setText(childText);

            }
        return convertView;
    }

    private void ChangedItemValues(int groupPosition,int childPosition, String value){
        List<String> childList;
        int size;
        switch(groupPosition){
            case 0:
                childList = _listDataChild.get(getGroup(groupPosition));
                size = childList.size();
                childList.clear();
                for(int i=0;i<size;i++){
                    childList.add(value);
                }
                break;
            case 1:
                childList = _listDataChild.get(getGroup(groupPosition));
                size = childList.size();
                childList.clear();
                for(int i=0;i<size;i++){
                    childList.add(value);
                }
                break;
            case 2:
                childList = _listDataChild.get(getGroup(groupPosition));
                size = childList.size();
                childList.clear();
                for(int i=0;i<size;i++){
                    childList.add(value);
                }
                break;
        }
        notifyDataSetChanged();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.table_header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.etChildItem);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}