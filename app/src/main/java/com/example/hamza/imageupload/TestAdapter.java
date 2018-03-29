package com.example.hamza.imageupload;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{

    private List<Item> items;
    Context context;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
        }
    };


    // Adapter's Constructor
    public TestAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
    }

    // Create new views. This is invoked by the layout manager.
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view by inflating the row item xml.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        // Set strings to the views
        final TextView itemType = (TextView) holder.view.findViewById(R.id.itemType);
        final TextView itemResult = (TextView) holder.view.findViewById(R.id.itemResult);
        final TextView itemDate = (TextView) holder.view.findViewById(R.id.itemDate);
        final RelativeLayout itemColor = (RelativeLayout) holder.view.findViewById(R.id.itemColor);
        try {
            //DateFormat f = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.S");
            //Date d = f.parse(items.get(position).DateHeure.date);
            //DateFormat time = new SimpleDateFormat("hh:mm:ss");
            //String timeAlert = time.format(d);
            //String[] timeAlertParts = items.get(position).DateHeure.date.split(" ");
            //String[] timeAlertDateWithMilli = timeAlertParts[1].split("\\.");
            //String timeAlert = timeAlertDateWithMilli[0];
            //itemDate.setText(""+timeAlert);
            itemType.setText(""+ items.get(position).type);
            itemResult.setText(""+ items.get(position).result);
            if (items.get(position).result>89){
                itemColor.setBackgroundColor(Color.parseColor("#cc0000"));
            } else if (items.get(position).result>84){
                itemColor.setBackgroundColor(Color.parseColor("#b20000"));
            } else if (items.get(position).result>79) {
                itemColor.setBackgroundColor(Color.parseColor("#e50000"));
            } else {
                itemColor.setBackgroundColor(Color.parseColor("#ff8000"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }
}
