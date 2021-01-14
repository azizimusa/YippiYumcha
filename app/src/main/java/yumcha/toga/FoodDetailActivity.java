package yumcha.toga;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import yumcha.toga.databinding.FoodDetailBinding;
import yumcha.toga.pojo.ListingPojo;

public class FoodDetailActivity extends AppCompatActivity {

    FoodDetailBinding view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this, R.layout.food_detail);
        view.setVm(this);

        setSupportActionBar(view.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String pojo = getIntent().getStringExtra("food");
        ListingPojo listingPojo = new Gson().fromJson(pojo, ListingPojo.class);

        loadContent(listingPojo);

    }

    private void loadContent(ListingPojo listingPojo) {

        getSupportActionBar().setTitle(listingPojo.getProductName());
        view.subtitle.setText(listingPojo.getProductDesc());
        view.star.setText(String.valueOf(listingPojo.getStar()));
        view.distance.setText(listingPojo.getDistance());
        view.voucher.setText(listingPojo.getPromoDesc());

        if (listingPojo.getOutletDesc() != null && listingPojo.getOutletDesc() > 0) {
            view.underVoucher.setText(listingPojo.getOutletDesc() + " 您附近的公司");
        }

        if (listingPojo.getOutletAround() != null && listingPojo.getOutletAround() > 0) {
            view.underVoucher.setText(listingPojo.getOutletAround() + " 您附近的公司");
        }

        if ((listingPojo.getOutletDesc() != null && listingPojo.getOutletDesc() <= 0) || (listingPojo.getOutletAround() != null && listingPojo.getOutletAround() <= 0)) {
            view.underVoucher.setVisibility(View.GONE);
        }

        Glide.with(this).load(listingPojo.getImageUrl()).into(view.image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon action bar is clicked; go to parent activity
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
