package myapplication.project;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import myapplication.bits.R;
import myapplication.bits.Utils;

public class InlineTablesActivity extends Activity {

    public static final int GROUP_COUNT = 3;
    public static final int CHILD_COUNT = 5;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private int lastExpandedPosition = -1;
    final SwipeToDelete swipeDetector = new SwipeToDelete();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inline_tables);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.elvParentTable);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnTouchListener(swipeDetector);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
    });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }

        });

        //deleting the items from list

        expListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {

                        long packedPosition = expListView.getExpandableListPosition(pos);
                        int itemType = ExpandableListView.getPackedPositionType(packedPosition);
                        int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                        int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);
                        if (swipeDetector.swipeDetected()) {
                            // do the onSwipe action
                            if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                                List<String> childList;
                                int size;
                                switch (groupPosition) {
                                    case 0:
                                        if (childPosition != 0) {
                                            childList = listDataChild.get(listAdapter.getGroup(groupPosition));
                                            childList.remove(childPosition);
                                        }
                                        break;
                                    case 1:
                                        if (childPosition != 0) {
                                            childList = listDataChild.get(listAdapter.getGroup(groupPosition));
                                            childList.remove(childPosition);
                                        }
                                        break;
                                    case 2:
                                        if (childPosition != 0) {
                                            childList = listDataChild.get(listAdapter.getGroup(groupPosition));
                                            childList.remove(childPosition);
                                        }
                                        break;
                                }
                                // Refresh the adapter
                                listAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding group data
        for (int i = 0; i < GROUP_COUNT; i++) {
            int count = i + 1;
            listDataHeader.add("Table " + i);
        }

        List<String> table1Items = new ArrayList<String>();
        // Adding child data
        for (int i = 0; i < CHILD_COUNT; i++) {
            int count = i + 1;
            table1Items.add("Item " + i);
        }

        List<String> table2Items = new ArrayList<String>();
        // Adding child data
        for (int i = 0; i < CHILD_COUNT; i++) {
            int count = i + 1;
            table2Items.add("Item " + i);
        }

        List<String> table3Items = new ArrayList<String>();
        // Adding child data
        for (int i = 0; i < CHILD_COUNT; i++) {
            int count = i + 1;
            table3Items.add("Item " + i);
        }

        listDataChild.put(listDataHeader.get(0), table1Items); // Header, Child data
        listDataChild.put(listDataHeader.get(1), table2Items);
        listDataChild.put(listDataHeader.get(2), table3Items);
    }

}
