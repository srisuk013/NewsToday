package com.srisuk.newstoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.srisuk.newstoday.models.Api;
import com.srisuk.newstoday.models.ListModel;
import com.srisuk.newstoday.models.NewResponse;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new CustomAdapter();

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        rv.setAdapter(mAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);
       api.fetchNews().enqueue(new Callback<NewResponse>() {
           @Override
           public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
               mAdapter.setList(response.body().getArticles());
           }

           @Override
           public void onFailure(Call<NewResponse> call, Throwable t) {

           }
       });

    }
    private class CustomAdapter extends RecyclerView.Adapter<CustomHolder> {

        private List<ListModel> list = new ArrayList<>();

        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent,false);
            return new CustomHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
            holder.tv1.setText(list.get(position).getTitle());
            Glide.with(holder.iv1)
                    .load(list.get(position).getUrlToImage())
                    .into(holder.iv1);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        void setList(List<ListModel> list) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }


    private class CustomHolder extends RecyclerView.ViewHolder {
        private final TextView tv1;
        private final ImageView iv1;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.text);
            iv1 = (ImageView) itemView.findViewById(R.id.image);
        }
    }


}