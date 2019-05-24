package com.a18mahkh.projekt;
/*import android.content.Intent;
import android.os.AsyncTask;*/
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
/*import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;*/
import java.util.ArrayList;

//import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    //ListView myListView;

    //private ArrayAdapter<Mountain> mountainAdapter;

    private static  final String TAG = "MainActivity";

    //vars

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls= new ArrayList<>();

    private ArrayList<String> sn_mNames = new ArrayList<>();
    private ArrayList<String> sn_mImageUrls= new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            getImages();
        //new FetchData().execute();


        //mountainAdapter=new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview,R.id.my_item_textview);

        /*myListView = (ListView) findViewById(R.id.my_Listview);
        myListView.setAdapter(mountainAdapter);
        myListView.setDivider(null);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), MountainDetailsActivity.class);
                String mountainDetails = mountainAdapter.getItem(position).info();

                intent.putExtra("mountainDetails", mountainDetails);

                startActivity(intent);


            }
        });
*/

    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");


        initRecycleView();

    }

    private void initRecycleView(){
        Log.d(TAG, "initRecycleView: init recyler View");

        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(layoutManager);

        RecycleViewAdapter adapter = new RecycleViewAdapter(this, mNames, mImageUrls);

        recyclerView.setAdapter(adapter);
    }



}



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id ==R.id.action_setting){

            // mountainAdapter.clear();
            //new FetchData().execute();
            Intent intent = new Intent(getApplicationContext(), MountainDetailsActivity.class);
            // String mountainDetails = mountainAdapter.getItem(position).info();

            intent.putExtra("mountainDetails", "dfdfdf");

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
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

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
                    try {

                        // JSONArray jsonFile = new JSONArray(o);

                        JSONObject jsonObject = new_array.getJSONObject(i);

                        String inName = jsonObject.getString("name");
                        String inLocation= jsonObject.getString("location");
                        int inHeight= jsonObject.getInt("size");
                        //String inImUrl = jsonObject.getString("auxdata");

                        Mountain mountains = new Mountain(inName, inLocation, inHeight);
                        mountainAdapter.add(mountains);




                    } catch (JSONException e) {
                        Log.e("brom", "E:" + e.getMessage());

                    }
                    // Implement a parsing code that loops through the entire JSON and creates objects
                    // of our newly created Mountain class.

                    //ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview,R.id.my_item_textview, name_array);
                    // myListView.setAdapter(adapter);

                    //Intent intent = new Intent(getApplicationContext(), MountainDetailsActivity.class);
                    // intent.putExtra(name_array);



                    //startActivity(intent);



                }
            }catch (JSONException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                // tv.setText("error2");
            }

        }
    }
}

*/