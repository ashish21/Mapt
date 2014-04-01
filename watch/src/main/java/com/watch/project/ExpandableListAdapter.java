package com.watch.project;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.watch.project.commentsendpoint.model.Comments;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dexter on 28-03-2014.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listOfComments;
    private HashMap<String, List<Comments>> listDataChild;

    public ExpandableListAdapter(Context context, List<String> listOfComments, HashMap<String, List<Comments>> listDataChild) {

        this.context = context;
        this.listOfComments = listOfComments;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {

        return this.listDataChild.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return this.listDataChild.get(this.listOfComments.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this.listDataChild.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.listDataChild.get(this.listOfComments.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Comments headerTitle = (Comments) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle != null ? headerTitle.getData().get(1) : null);
        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Comments childComment = (Comments)getChild(groupPosition, childPosition);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childComment != null ? childComment.getData().get(0) : null);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {

        return true;
    }
}
