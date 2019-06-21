package com.example.marvelrecyclerveiw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements RecyclerViewadapter.ItemClickListener {

    // List<HeroActivity> HeroList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // new YourAsyncTask(this).execute();
        getHeroList();
    }

    @Override
    public void onItemClick(View view, int position) {
        HeroActivity heroActivity =adapter.getItem(position);
        Toast.makeText(this, heroActivity.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemNameClick(View view, int position) {
         HeroActivity H=adapter.getItem(position);
        Toast.makeText(this,H.getImageurl(), Toast.LENGTH_SHORT).show();
    }



    private void getHeroList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiActivity api = retrofit.create(ApiActivity.class);

        Call<List<HeroActivity>> call = api.getHeroes();

        call.enqueue(new Callback<List<HeroActivity>>() {
            @Override
            public void onResponse(Call<List<HeroActivity>> call, Response<List<HeroActivity>> response) {

                List<HeroActivity> HeroList = response.body();

                //Creating an String array for the ListView
                Toast.makeText(MainActivity.this, "OKK", Toast.LENGTH_SHORT).show();
                adapter = new RecyclerViewadapter(MainActivity.this, HeroList);
                adapter.setClickListener(MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<HeroActivity>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


//    private class YourAsyncTask extends AsyncTask<String, Void, String> {
//        private ProgressDialog dialog;
//
//        public YourAsyncTask(MainActivity activity) {
//            dialog = new ProgressDialog(activity);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            dialog.setMessage("Doing something, please wait.");
//            dialog.show();
//        }
//
//        protected String doInBackground(String... args) {
//
//
//            String url = "http://api.myjson.com/bins/d5y1e";
//            String jsonStr = "";
//            try {
//                // Making a request to url and getting response
//                HttpClient client = new DefaultHttpClient();
//                HttpGet request = new HttpGet();
//                request.setURI(new URI(url));
//                HttpResponse response = client.execute(request);
//                jsonStr = EntityUtils.toString(response.getEntity());
//            } catch (MalformedURLException e) {
//                Log.e("MainActivity", "MalformedURLException: " + e.getMessage());
//            } catch (ProtocolException e) {
//                Log.e("MainActivity", "ProtocolException: " + e.getMessage());
//            } catch (IOException e) {
//                Log.e("MainActivity", "IOException: " + e.getMessage());
//            } catch (Exception e) {
//                Log.e("MainActivity", "Exception: " + e.getMessage());
//            }
//
//
////            String str=args[0];
////            // do background work here
////            try {
////                Thread.sleep(4000);
////            } catch (InterruptedException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
////
//
//            return jsonStr;
//        }
//
//        protected void onPostExecute(String result) {
//
//
//            try {
//                // JSONObject mainObj=new JSONObject(result);
//
//                JSONArray arrObj = new JSONArray(result);
//
//                for (int item = 0; item < arrObj.length(); item++) {
//                    Product product = new Product();
//
//                    JSONObject jsonObject = arrObj.getJSONObject(item);
//
//                    product.setProductName(jsonObject.getString("productName"));
//                    product.setImageUrl(jsonObject.getString("imageUrl"));
//
//                    productList.add(product);
//                }
//                adapter = new RecyclerViewAdapter(MainActivity.this, productList);
//                adapter.setClickListener(MainActivity.this);
//                recyclerView.setAdapter(adapter);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//            // do UI work here
//            if (dialog.isShowing()) {
//                dialog.dismiss();
//            }
//
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//
////            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
////            startActivity(intent);
//        }
//    }
}
