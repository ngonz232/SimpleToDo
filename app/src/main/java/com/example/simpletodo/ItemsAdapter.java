package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        //Gets position of what item was longclicked
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        //sets variable to one passed through constructor
        this.items = items;
        //defines longClickListener variable
        this.longClickListener = longClickListener;
        //defines variable for OnClickListener
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    //Creates each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Implement Layout Inflater to inflate view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //Wraps it inside ViewHolder and returns the view
        return new ViewHolder(todoView);

    }

    //Takes the data and puts it into a ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        //Grab the item at a position
        String item = items.get(position);

        //Bind it to specified ViewHolder
        holder.bind(item);

    }

    //Tells recycler view how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views representing each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        //Define TextView
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Updates the view in the ViewHolder with the string data from items
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                   clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notifies adapter which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;

                }
            });
        }
    }
}
