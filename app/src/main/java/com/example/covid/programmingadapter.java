package com.example.covid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class programmingadapter extends RecyclerView.Adapter<programmingadapter.programmingviewholder> {

    private String[] data;
    private String[] data1;
    private String[] data2;
    private String[] data3;
    public programmingadapter(String[] da,String[] da1,String[] da2,String[] da3){
        data=da;
        data1=da1;
        data2=da2;
        data3=da3;

    }
    @NonNull
    @Override
    public programmingviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);

        return new programmingviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull programmingviewholder holder, int position) {

        holder.p1.setText(data[position]);
        holder.p2.setText(data1[position]);
        holder.p3.setText(data2[position]);
        holder.p4.setText(data3[position]);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class programmingviewholder extends RecyclerView.ViewHolder{
        TextView p1,p2,p3,p4;

        public programmingviewholder(@NonNull View itemView) {

            super(itemView);
            p1 = itemView.findViewById(R.id.t1);
            p2 = itemView.findViewById(R.id.t2);
            p3 = itemView.findViewById(R.id.t3);
            p4 = itemView.findViewById(R.id.t4);
        }
    }
}
