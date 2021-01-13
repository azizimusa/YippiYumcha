package yumcha.toga;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yumcha.toga.databinding.ActivityMainBinding;
import yumcha.toga.pojo.ListingPojo;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding view;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this, R.layout.activity_main);
        view.setVm(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.toolbar_color));
        TextView tt = getSupportActionBar().getCustomView().findViewById(R.id.title);
        tt.setText("带走");

        getListing();

    }

    private void initRV(List<ListingPojo> listingPojos) {
        adapter = new MyAdapter(this, listingPojos, new MyAdapter.OnFoodClickListener() {
            @Override
            public void onClick(ListingPojo pojo) {
                Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                intent.putExtra("food", new Gson().toJson(pojo));
                startActivity(intent);
            }
        });

        view.rv.setAdapter(adapter);
        view.rv.setLayoutManager(new LinearLayoutManager(this));
        view.rv.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    private void getListing() {
        Call<ResponseBody> call = MyApp.getInstance().getRestApi().getListing();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {

                    try {
                        String json = response.body().string();

                        Type collectionType = new TypeToken<Collection<ListingPojo>>(){}.getType();
                        Collection<ListingPojo> listingPojos = new Gson().fromJson(json, collectionType);

                        initRV((List<ListingPojo>) listingPojos);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}