package com.ortegapatriciaa.enventer;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ortegapatriciaa on 9/21/2017.
 */

public class EventFullinfo_Adapter extends ArrayAdapter<EventInfo> {

    private Activity context;
    List<EventInfo> eventInfoList;

    public EventFullinfo_Adapter(Activity context, List<EventInfo> eventInfoList){
        super(context, R.layout.event_information, eventInfoList);

        this.context = context;
        this.eventInfoList = eventInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.event_information, null, true);

        TextView infoEventTitle = (TextView) listViewItem.findViewById(R.id.displayinfoEventTitle);
        TextView infoEventDate = (TextView) listViewItem.findViewById(R.id.displayinfoEventDate);
        TextView infoEventTime = (TextView) listViewItem.findViewById(R.id.displayinfoEventTime);
        TextView infoEventLocation = (TextView) listViewItem.findViewById(R.id.displayinfoEventLocation);
        TextView infoEventDescription = (TextView) listViewItem.findViewById(R.id.displayinfoEventDescription);

        EventInfo eventInfo = eventInfoList.get(position);

        infoEventTitle.setText(eventInfo.getEventTitle());
        infoEventDate.setText(eventInfo.getDateStart()); //add some date end
        infoEventTime.setText(eventInfo.getTimeStart() + " - " + eventInfo.getTimeEnd());
        infoEventLocation.setText(eventInfo.getLocation());
        infoEventDescription.setText(eventInfo.getDescription());

        return listViewItem;
    }
}
