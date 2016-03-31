package com.laptop.flightstatus.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laptop.flightstatus.R;
import com.laptop.flightstatus.model.OperatingFlightLeg;

import java.util.ArrayList;
import java.util.List;



public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{

    List<OperatingFlightLeg> mItems;

    public StatusAdapter() {
        super();
        mItems = new ArrayList<>();
    }

    public void addData(OperatingFlightLeg leg) {

         mItems.add(leg);
         notifyDataSetChanged();

    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        OperatingFlightLeg leg = mItems.get(i);
        viewHolder.stat.setText(leg.getFlightStatus());
        viewHolder.deptfrom.setText(leg.getDepartsFrom().getIATACode());
        viewHolder.deptime.setText(leg.getScheduledDepartureDateTime());
        viewHolder.arrivtime.setText(leg.getScheduledArrivalDateTime());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView stat;
        public TextView deptfrom;
        public TextView deptime;
        public TextView arrivtime;

        public ViewHolder(View itemView) {
            super(itemView);
            stat = (TextView) itemView.findViewById(R.id.status);
            deptfrom = (TextView) itemView.findViewById(R.id.departsFrom);
            deptime = (TextView) itemView.findViewById(R.id.departTime);
            arrivtime = (TextView) itemView.findViewById(R.id.arrivalTime);
        }
    }

}
