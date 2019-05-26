package com.a18mahkh.projekt;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;

    private List<Movie_itemlist> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the views from xml

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        //initializing adapter
        listItems = new ArrayList();


        recyclerAdapter=new RecyclerViewAdapter(listItems, getApplicationContext());

        recyclerView.setAdapter(recyclerAdapter);


        new FetchData().execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id ==R.id.action_setting){

            Intent intent = new Intent(getApplicationContext(), About.class);

            startActivity(intent);

            return true;
        }
        else{
            //return false;
            return super.onOptionsItemSelected(item);
        }
    }



    private class FetchData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a18mahkh");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.
            Log.d("brom", "dataFetched:" + o);

            //String response = o.toString();
            //List<String> listData = new ArrayList<String>();
            //  JSONArray new_array = new JSONArray(jsonStr);
            try {
                JSONArray new_array = new JSONArray(o);

                for (int i = 0; i < new_array.length(); i++) {
                        // JSONArray jsonFile = new JSONArray(o);

                        JSONObject jsonObject = new_array.getJSONObject(i);

                        Movie_itemlist item = new Movie_itemlist(
                                jsonObject.getString("name"),
                                jsonObject.getString("company"),
                                jsonObject.getInt("size"),
                                jsonObject.getInt("cost"),
                                jsonObject.getString("location"),
                                jsonObject.getString("category"),
                                jsonObject.getString("auxdata")
                        );


                        listItems.add(item);

                    }

                    recyclerAdapter = new RecyclerViewAdapter(listItems, getApplicationContext());
                    recyclerView.setAdapter(recyclerAdapter);

            }catch (JSONException e) {
                // TODO Auto-generated catch block
            }

        }
    }








   /* private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONArray json_array = new JSONArray(response);
                    for (int i = 0; i < json_array.length(); i++) {

                        JSONObject jsonObject = json_array.getJSONObject(i);

                        String movie_title = jsonObject.getString("name");
                        String movie_description = jsonObject.getString("location");
                        String imgUrl = jsonObject.getString("auxdata");

                        Movie_itemlist movie_itemlist = new Movie_itemlist(movie_title, movie_description);
                        listItems.add(movie_itemlist);
                    }
                    recyclerAdapter = new RecyclerViewAdapter(listItems, getApplicationContext());
                    recyclerView.setAdapter(recyclerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }*/

}

