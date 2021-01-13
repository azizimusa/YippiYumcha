package yumcha.toga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import yumcha.toga.databinding.ListingRowBinding;
import yumcha.toga.pojo.ListingPojo;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<ListingPojo> listingPojos;
    private OnFoodClickListener onFoodClickListener;

    public interface OnFoodClickListener {
        void onClick(ListingPojo pojo);
    }

    public MyAdapter(Context context, List<ListingPojo> listingPojos, OnFoodClickListener onFoodClickListener) {
        this.context = context;
        this.listingPojos = listingPojos;
        this.onFoodClickListener = onFoodClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.listing_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListingPojo pojo = listingPojos.get(position);
        holder.view.title.setText(pojo.getProductName());
        holder.view.subtitle.setText(pojo.getProductDesc());
        holder.view.star.setText(String.valueOf(pojo.getStar()));
        holder.view.range.setText(pojo.getDistance());
        holder.view.voucher.setText(pojo.getPromoDesc());

        if (pojo.getOutletDesc() != null && pojo.getOutletDesc() > 0) {
            holder.view.underVoucher.setText(pojo.getOutletDesc() + " 您附近的公司");
        }

        if (pojo.getOutletAround() != null && pojo.getOutletAround() > 0) {
            holder.view.underVoucher.setText(pojo.getOutletAround() + " 您附近的公司");
        }

        if ((pojo.getOutletDesc() != null && pojo.getOutletDesc() <= 0) || (pojo.getOutletAround() != null && pojo.getOutletAround() <= 0)) {
            holder.view.underVoucher.setVisibility(View.GONE);
        }

        Glide.with(context).load(pojo.getImageUrl()).into(holder.view.iv);

        holder.view.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFoodClickListener.onClick(pojo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listingPojos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ListingRowBinding view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = DataBindingUtil.bind(itemView);
        }
    }
}
