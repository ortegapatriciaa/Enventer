package com.ortegapatriciaa.enventer;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ortegapatriciaa on 9/15/2017.
 */

public class EventInfo_Adapter extends ArrayAdapter<EventInfo>{

    private Activity context;
    List<EventInfo> eventInfos;

    public EventInfo_Adapter(Activity context, List<EventInfo> eventInfos){
        super(context, R.layout.events_list, eventInfos);

        this.context = context;
        this.eventInfos = eventInfos;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.events_list, null, true);

        TextView eventTitle = (TextView) listViewItem.findViewById(R.id.displayEventTitle);
        TextView eventDate = (TextView) listViewItem.findViewById(R.id.displayEventDate);
        TextView eventTime = (TextView) listViewItem.findViewById(R.id.displayEventTime);
        TextView eventLocation = (TextView) listViewItem.findViewById(R.id.displayEventLocation);

        EventInfo eventInfo = eventInfos.get(position);
        eventTitle.setText(eventInfo.getEventTitle());
        eventDate.setText(eventInfo.getDateStart());
        eventTime.setText(eventInfo.getTimeStart() + " - " + eventInfo.getTimeEnd());
        eventLocation.setText(eventInfo.getLocation());

        return listViewItem;

    }
}
