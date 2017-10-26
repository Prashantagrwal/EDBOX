package com.example.dell.edbox;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dell.edbox.pojo.JsonClass;
import com.example.dell.edbox.realm.RealmPojo;
import com.example.dell.edbox.retrofit.InterfaceRetro;
import com.example.dell.edbox.retrofit.RetroCall;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    Realm realm;
     ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

       loading  = ProgressDialog.show(MainActivity.this, "", "loading...", false, false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        Realm.init(MainActivity.this);

        realm = Realm.getDefaultInstance();

        if (new CheckConnection(MainActivity.this).checkInternetConenction()) {
            Call<JsonClass> call = null;
            InterfaceRetro apiService = RetroCall.getClient().create(InterfaceRetro.class);
            call = apiService.getData();
            Log.e("value", "inside");
            call.enqueue(new Callback<JsonClass>() {
                @Override
                public void onResponse(Call<JsonClass> call, Response<JsonClass> response) {
                    JsonClass jsonClass = response.body();
                    CheckRealm(jsonClass);
                    Adapter adapter = new Adapter(jsonClass, MainActivity.this);
                    loading.dismiss();
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<JsonClass> call, Throwable t) {
                    Log.e("error", t.getMessage());
                }
            });
        } else {
            if(realm.isEmpty()) {
                TextView t=(TextView) findViewById(R.id.text_empty);
                loading.dismiss();
                t.setVisibility(View.VISIBLE);
            }
            else
                {
                    RealmResults<RealmPojo> result=realm.where(RealmPojo.class).findAll();
                    ArrayList<RealmPojo> res=new ArrayList<>();
                    res.addAll(result);
                    RealmAdapter realmAdp=new RealmAdapter(res,MainActivity.this);
                    loading.dismiss();
                    recyclerView.setAdapter(realmAdp);
            }
        }
    }
    private void CheckRealm(JsonClass jsonClass) {

        if(realm.isEmpty()){

            ArrayList<RealmPojo> data=new ArrayList<>();

            for(int i=0;i<jsonClass.getData().getChildren().size();i++){
                RealmPojo realmPojo=new RealmPojo();
                Log.e("run", String.valueOf(i));
                realmPojo.setId(i+ System.currentTimeMillis());
                realmPojo.setTitle(jsonClass.getData().getChildren().get(i).getData().getTitle());
                realmPojo.setComment(jsonClass.getData().getChildren().get(i).getData().getNumComments().toString());
                realmPojo.setLikes(jsonClass.getData().getChildren().get(i).getData().getUps().toString());
                realmPojo.setDislikes(jsonClass.getData().getChildren().get(i).getData().getDowns().toString());
                data.add(realmPojo);
            }
            for (RealmPojo b : data) {
                // Persist your data easily
                Log.e("run", "x");
                realm.beginTransaction();
                realm.copyToRealm(b);
                realm.commitTransaction();
            }
    }

    }
}
