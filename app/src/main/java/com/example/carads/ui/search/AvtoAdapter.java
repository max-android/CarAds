package com.example.carads.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.model.storage.database.entity.Car;


import java.util.Collections;
import java.util.List;


public class AvtoAdapter extends RecyclerView.Adapter<AvtoAdapter.ViewHolder> {

    private List<Car> results;

    private final  RequestManager requestManager;

    private final CarClickListener listener;


    public AvtoAdapter(List<Car> results, RequestManager requestManager, CarClickListener listener) {
        this.results = results;
        this.requestManager = requestManager;
        this.listener = listener;
    }

    @Override
    public AvtoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.avto_item,parent,false);

       // return  new ViewHolder(view,listener);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AvtoAdapter.ViewHolder holder, int position) {

        Car car=results.get(position);
        holder.bindTo(car);

    }


    @Override
    public int getItemCount() {
        return results.size();
    }



    public void adOnMove(int fromPos,int toPos) {


        Collections.swap(results,fromPos,toPos);
        notifyItemMoved(fromPos, toPos);
    }

    public void adOnSwiped(RecyclerView.ViewHolder viewHolder) {
        int swipedPosition = viewHolder.getAdapterPosition();
        results.remove(swipedPosition);

        notifyItemRemoved(swipedPosition);
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView tvName;
        private final TextView tvIssue;
        private final TextView tvPrice;
        private final TextView tvMileage;

        private Car car;

       // public ViewHolder(View itemView,final CarClickListener listener) {
        public ViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.imageAvto);
            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvIssue=(TextView)itemView.findViewById(R.id.tvIssue);
            tvPrice=(TextView)itemView.findViewById(R.id.tvPrice);
            tvMileage=(TextView)itemView.findViewById(R.id.tvMileage);

                itemView.setOnClickListener(view ->launchCar(imageView) );
        }


        private void launchCar(View view){

            listener.onCarClick(car,view);

        }

        public  void bindTo(Car car){

            this.car=car;
            tvName.setText(car.getName());
            tvIssue.setText(car.getDate_issue());
            tvPrice.setText(String.valueOf(car.getPrice())+" Р");
            tvMileage.setText(car.getMileage());

            requestManager.load(car.getImage()).into(imageView);
        }
    }


public interface CarClickListener{


    void onCarClick(Car car,View view);
}

}
