package com.watch.project;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidmapsextensions.ClusterGroup;
import com.androidmapsextensions.ClusteringSettings;
import com.androidmapsextensions.GoogleMap;
import com.androidmapsextensions.Marker;
import com.androidmapsextensions.MarkerOptions;
import com.androidmapsextensions.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.memetix.mst.translate.Translate;
import com.watch.project.commentidsendpoint.Commentidsendpoint;
import com.watch.project.commentidsendpoint.model.CommentIds;
import com.watch.project.commentsendpoint.Commentsendpoint;
import com.watch.project.commentsendpoint.model.Comments;
import com.watch.project.reportsendpoint.Reportsendpoint;
import com.watch.project.reportsendpoint.model.CollectionResponseReports;
import com.watch.project.reportsendpoint.model.Reports;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.HashMap;

import java.util.Locale;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private ProgressBar mProgress;
    private String sysLang="";
    private CharSequence mTitle;
    private static List<Reports> parents;
    private static LatLng clickLoc, loc2;
    private GoogleMap mMap;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView textView ;
    private ArrayList<String> refArr = new ArrayList<String>();
    private AlertDialog alertDialog;
    private static Context superContext;
    private String showing = null;
    private ArrayList<String> addressesAll = new ArrayList<String>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        superContext = this;
        sysLang = Locale.getDefault().getDisplayLanguage();
        Translate.setClientId("neighbourhood_watch");
        Translate.setClientSecret("fHbFTVnTMEnqZ4Bw4R+LeU3PE36EcmPz+hXKAnUHXKs=");
        Toast.makeText(this, "Fetching Data from server", Toast.LENGTH_LONG).show();

        new temp().execute();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new PlaceholderFragment(), "Main")
                .commit();
        Log.d("Ayush", "Total fragments attached first call"+fragmentManager.getFragments().size());
    }

    private class temp extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CloudStorage.file = StreamUtil.stream2file(getAssets().open("key.p12"));
                        List<String> temp = CloudStorage.listBuckets();
                        Log.d("Ayush", "Size of buckets = " + temp.size());
                        for(String a : temp) Log.d("Ayush", "bucket= "+a);
                        Log.d("Ayush", "OK");
                        CloudStorage.createBucket("publicfortress.appspot.com");
                        Log.d("Ayush", "OK");
                        CloudStorage.uploadFile("publicfortress.appspot.com", "/mnt/sdcard/Output/test.jpg");
                    } catch (IOException e) {
                        Log.e("Ayush", "hmmm"+e.getMessage());
                    } catch (Exception e) {
                        Log.e("Ayush", "hmmm"+e.toString());
                    }
                }
            });
            thread.run();
            return null;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.click, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_report) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack("Main")
                    .replace(R.id.container, new MyDialogFragment(), "Dialog")
                    .commit();            return true;
        } else if(id == R.id.action_fetch) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

//        Log.v("Ayush", ""+position);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .addToBackStack("Main")
//                .replace(R.id.container, new Report(new Reports()), "Report")
//                .commit();
//        Log.d("Ayush", "Total fragments attached with report"+fragmentManager.getFragments().size());
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    public static HashMap<String, List<Comments>> listDataChild = new HashMap<String, List<Comments>>();
    public static ExpandableListView listOfComments = null;
    public static ExpandableListAdapter listAdapter = null;
    public static Context reportContext = null;
    public final static List<String> listDataHeader = Arrays.asList("View All Comments", "Top Comments");

    public class Report extends Fragment {

        public Report(Reports report) {

            this.report = report;
        }

        private Reports report;
        private View rootView;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if(rootView != null) {

                Log.v("Ayush", "removing root view Report");
                ViewGroup parent = (ViewGroup) rootView.getParent();
                if (parent != null) {

                    parent.removeView(rootView);
                }
                rootView.invalidate();
                return rootView;
            } else {

                rootView = inflater.inflate(R.layout.fragment_report, container, false);
            }

            reportContext = container.getContext();

            HorizontalScrollView gallery = (HorizontalScrollView) (rootView != null ? rootView.findViewById(R.id.gallery) : null);
            ImageView noOfFollowers = (ImageView) (rootView != null ? rootView.findViewById(R.id.followImage) : null);
            LinearLayout tags = (LinearLayout)rootView.findViewById(R.id.tags);
            TextView title = (TextView)rootView.findViewById(R.id.title);
            TextView description = (TextView)rootView.findViewById(R.id.description);
            TextView address = (TextView)rootView.findViewById(R.id.address);
            TextView DOS = (TextView)rootView.findViewById(R.id.DOS);
            Button flag = (Button)rootView.findViewById(R.id.flag);
            Button follow = (Button)rootView.findViewById(R.id.follow);
            final EditText addComment = (EditText)rootView.findViewById(R.id.addComment);
            Button postComment = (Button)rootView.findViewById(R.id.postComment);

            postComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Object [] objects = new Object[2];
                    objects[0] = report.getId();
                    objects[1] = (Html.toHtml(addComment.getText())).toString();
                    new AddComment().execute(objects);
                }
            });

            new ListOfCommentIdsAsyncRetriever().execute(report.getId());

            if(listOfComments == null) {
                listOfComments = (ExpandableListView)rootView.findViewById(R.id.comments);
            }

            for(String a : report.getTags()) {

                TextView temp = new TextView(container.getContext());
                temp.setText(Html.fromHtml(a));
                tags.addView(temp);
            }
            ///
            title.setText(report.getTitle());
            ///
            description.setText(Html.fromHtml(report.getInfo()));
            ///
            address.setText(report.getAddress());
            ///
            DOS.setText(report.getDos().toStringRfc3339());
            ///
            return rootView;
        }
    }

    public class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
            Log.v("Ayush", "Constr Called");
        }

        private Context context;
        private View rootView;

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            if(rootView != null) {

                Log.v("Ayush", "removing root view");
                ViewGroup parent = (ViewGroup) rootView.getParent();
                if (parent != null) {

                    parent.removeView(rootView);
                }
                rootView.invalidate();
                return rootView;
            } else {

                rootView = inflater.inflate(R.layout.fragment_main, container, false);
            }
            Log.v("Ayush", "CREATING rootview");
            context = getActivity().getApplication();

            mProgress = (ProgressBar)rootView.findViewById(R.id.progressBar);
            adapter = new ArrayAdapter<String>(context, R.layout.item_list);
            textView = (AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView1);
            adapter.setNotifyOnChange(true);
            textView.setAdapter(adapter);

            textView.addTextChangedListener(new TextWatcher() {

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count%3 == 1) {
                        adapter.clear();
                        GetPlaces task = new GetPlaces();
                        //now pass the argument in the textview to the task
                        task.execute(textView.getText().toString());
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                public void afterTextChanged(Editable s) {

                }
            });
            textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2,
                                        long arg3) {
                    Log.e("arg2=", String.valueOf(arg3));
                    GoPlaces task=new GoPlaces();
                    if(refArr.size() == 0) return;
                    task.execute(refArr.get(arg2));
                }
            });


            LatLng def = new LatLng(28.6075, 77.035);
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);

            mMap = mapFrag.getExtendedMap();
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            Log.v("Ayush", "Size= "+mMap.getMarkers().size());
            mMap.clear();
            Log.v("Ayush", "Size= "+mMap.getMarkers().size());

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(def, 13));
            mMap.setMyLocationEnabled(true);

            mMap.setClustering(new ClusteringSettings());
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack("Main")
                            .replace(R.id.container, new Report((Reports) marker.getData()), "Report")
                            .commit();
                    Log.d("Ayush", "Total fragments attached with report"+fragmentManager.getFragments().size());

                }
            });
            final Marker single = mMap.addMarker(new MarkerOptions().position(new LatLng(10, 10)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).clusterGroup(ClusterGroup.NOT_CLUSTERED));
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                    clickLoc = latLng;
                    single.setPosition(latLng);
                    new GetAddressTask(context).execute(latLng);
                }
            });
            if(parents==null || parents.size() == 0)
               new ListOfParentsAsyncRetriever().execute();
            return rootView;
        }
    }

    public void addMarker(List<Reports> parents) {

        if(parents == null) return;

        if(parents.size() == 0) return;

        for(Reports parent : parents) {

            MarkerOptions temp = new MarkerOptions();
            Log.v("Ayush", sysLang);
            temp.position(new LatLng(Double.parseDouble(parent.getLat()), Double.parseDouble(parent.getLong())));
            temp.title(parent.getAddress());
            temp.snippet(Html.fromHtml(parent.getInfo()).toString());
            temp.clusterGroup(ClusterGroup.DEFAULT);
            temp.data(parent);
            mMap.addMarker(temp);
        }
    }

    public class MyDialogFragment extends Fragment {


        private LinearLayout ChooseAddress;
        private EditText ProvideDescription;
        private LinearLayout ChooseTags;
        private EditText ProvideTitle;
        private LinearLayout AddPhotos;
        private Context context;
        private Animation hyperspaceJumpAnimation;
        private ImageButton AddTags, PhotosAdd;
        private LatLng temp;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            setMenuVisibility(true);
            super.onCreate(savedInstanceState);
            temp = clickLoc;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.report_dialog, container, false);
            ChooseAddress = (LinearLayout) (rootView != null ? rootView.findViewById(R.id.ChooseAddress) : null);
            ProvideDescription = (EditText) (rootView != null ? rootView.findViewById(R.id.ProvideDescription) : null);
            ChooseTags = (LinearLayout) (rootView != null ? rootView.findViewById(R.id.ChooseTags) : null);
            ProvideTitle = (EditText) (rootView != null ? rootView.findViewById(R.id.ProvideTitle) : null);
            AddPhotos = (LinearLayout) (rootView != null ? rootView.findViewById(R.id.AddPhotos) : null);
            AddTags = (ImageButton) (rootView != null ? rootView.findViewById(R.id.AddTags) : null);
            PhotosAdd = (ImageButton) (rootView != null ? rootView.findViewById(R.id.PhotosAdd) : null);
            context = container.getContext();
            hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.submit_report);
            return rootView;
        }

        public void state(int counter) {

            switch (counter) {

                case 0 : address();break;
                case 1 : description();break;
                case 2 : tags();break;
                case 3 : title();break;
                case 4 : photos();break;
                case 5 : collectDataAndStore();break;
            }
        }

        public void collectDataAndStore() {

            Reports report = new Reports();
            report.setUser("Ayush");
            if(temp != null) {

                report.setLat(temp.latitude+"");
                report.setLong(temp.longitude+"");
            } else; // TODO

            report.setDos(new DateTime(new Date()));
            Log.d("Ayush", report.getDos() + "");

            for(int i=0; i<ChooseAddress.getChildCount(); i++) {

                if(((CheckBox)ChooseAddress.getChildAt(i)).isChecked()) report.setAddress(((CheckBox)ChooseAddress.getChildAt(i)).getText().toString());
            }

            report.setTitle((Html.toHtml(ProvideTitle.getText())).toString());

            report.setFlags(0);
            report.setFollowers(0);
            report.setInfo(Html.toHtml(ProvideDescription.getText()));
            List<String> tags = new ArrayList<String>();
            for(int i=1; i<ChooseTags.getChildCount(); i++) {

                tags.add(Html.toHtml(((EditText) ChooseTags.getChildAt(i)).getText()));
            }
            report.setTags(tags);
            new ParentInsert().execute(report);
            getSupportFragmentManager().popBackStack();
        }

        public void address() {

            if(addressesAll.size() == 0 || addressesAll.get(0).equals(null) || addressesAll == null) {

                state(counter++);
                return;
            }

            final CheckBox [] box = new CheckBox[addressesAll.size()];
            for(int i=0; i<addressesAll.size(); i++) {

                box[i] = new CheckBox(context);
                box[i].setText(addressesAll.get(i));
                ChooseAddress.addView(box[i]);
            } for(int i=0; i<addressesAll.size(); i++) {

                final int marker = i;
                box[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if(b) for(int j=0; j<addressesAll.size(); j++) {

                            if(j != marker) {
                                box[j].setChecked(false);
                            }
                        }
                    }
                });
            }



            ChooseAddress.startAnimation(hyperspaceJumpAnimation);
            ChooseAddress.setVisibility(View.VISIBLE);
        }

        public void description() {

            ProvideDescription.startAnimation(hyperspaceJumpAnimation);
            ProvideDescription.setVisibility(View.VISIBLE);
        }

        public void tags() {

            ChooseTags.startAnimation(hyperspaceJumpAnimation);
            ChooseTags.setVisibility(View.VISIBLE);
            AddTags.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChooseTags.addView(new EditText(context));
                }
            });
        }

        public void title() {

            ProvideTitle.startAnimation(hyperspaceJumpAnimation);
            ProvideTitle.setVisibility(View.VISIBLE);
        }

        public void photos() {

            AddPhotos.startAnimation(hyperspaceJumpAnimation);
            AddPhotos.setVisibility(View.VISIBLE);
        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            if (!mNavigationDrawerFragment.isDrawerOpen()) {
                // Only show items in the action bar relevant to this screen
                // if the drawer is not showing. Otherwise, let the drawer
                // decide what to show in the action bar.
                menu.clear();
                inflater.inflate(R.menu.report, menu);
                restoreActionBar();
            }
            super.onCreateOptionsMenu(menu, inflater);
        }

        int counter = 0;
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.Cancel) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack("Dialog")
                        .replace(R.id.container, new MyDialogFragment(), "Main")
                        .commit();
                return true;
            } else if(id == R.id.Continue) {

                Log.d("Ayush", "Continue clicked");
                state(counter++);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }

    private class GetAddressTask extends AsyncTask<LatLng, Void, ArrayList<String>> {

        Context mContext;
        public GetAddressTask(Context context) {
            super();
            mContext = context;
        }
        /**
         * Get a Geocoder instance, get the latitude and longitude
         * look up the address, and return it
         *
         * @params params One or more Location objects
         * @return A string containing the address of the current
         * location, or an empty string if no address can be found,
         * or an error message
         */
        @Override
        protected ArrayList<String> doInBackground(LatLng... params) {

            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            ArrayList<String> toSend = new ArrayList<String>(3);
            int score[] = new int[4];
            for(int i=0; i<4; i++) score[i] = 0;
            // Get the current location from the input parameter list
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
                /*
                 * Return 1 address.
                 */
                Log.d("Ayush", params[0].latitude +" "+ params[0].longitude);
                addresses = geocoder.getFromLocation(params[0].latitude,
                        params[0].longitude, 4);
            } catch (IOException e1) {
                Log.e("LocationSampleActivity",
                        e1.toString());
                return new ArrayList<String>(0);
            } catch (IllegalArgumentException e2) {
                // Error message to post in the log
                String errorString = "Illegal arguments " +
                        Double.toString(params[0].latitude) +
                        " , " +
                        Double.toString(params[0].longitude) +
                        " passed to address service";
                Log.e("LocationSampleActivity", errorString);
                e2.printStackTrace();
                return new ArrayList<String>(0);
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address

                int counter = 0;
                for(Address a : addresses) {

                    if(!(a.getCountryName() == null || a.getCountryName().equals(null))) score[counter]++;
                    if(!(a.getAdminArea() == null || a.getAdminArea().equals(null))) score[counter]++;
                    if(!(a.getFeatureName() == null || a.getFeatureName().equals(null))) score[counter]++;
                    if(!(a.getSubLocality() == null || a.getSubLocality().equals(null))) score[counter]++;
                    if(!(a.getLocality() == null || a.getLocality().equals(null))) score[counter]++;
                    if(!(a.getSubAdminArea() == null || a.getSubAdminArea().equals(null))) score[counter]++;
                    counter++;
                }

                int min=0;
                for(int i=0; i<counter; i++) {

                    if(score[i] <= score[min]) min = i;
                }

                addresses.remove(min);

                for(Address a : addresses) {

                    String finalAddress = "";
                    if(!(a.getFeatureName() == null || a.getFeatureName().equals(null))) finalAddress+=a.getFeatureName()+", ";
                    if(!(a.getSubLocality() == null || a.getSubLocality().equals(null))) finalAddress+=a.getSubLocality()+", ";
                    if(!(a.getSubAdminArea() == null || a.getSubAdminArea().equals(null))) finalAddress+=a.getSubAdminArea()+", ";
                    if(!(a.getLocality() == null || a.getLocality().equals(null))) finalAddress+=a.getLocality()+", ";
                    if(!(a.getAdminArea() == null || a.getAdminArea().equals(null))) finalAddress+=a.getAdminArea()+", ";
                    if(!(a.getCountryName() == null || a.getCountryName().equals(null))) finalAddress+=a.getCountryName()+", ";
                    toSend.add(finalAddress);
                    Log.v("Ayush", finalAddress);
                }

                return toSend;
            } else {
                return new ArrayList<String>(0);
            }
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {

            addressesAll.clear();
            if(result.size() > 3) addressesAll.add("?");
            else addressesAll = result;
        }
    }

    private class GoPlaces extends AsyncTask<String, Void, LatLng> {
        protected LatLng doInBackground(String... arg0) {
            try {
                URL googlePlaces = new URL(
                        // URLEncoder.encode(url,"UTF-8");
                        "https://maps.googleapis.com/maps/api/place/details/json?reference="+ URLEncoder.encode(arg0[0], "UTF-8") +"&sensor=true&key=AIzaSyAaliOe90JD33vEL3pq4QHYSvgXgW0PKeM");
                Log.v("Ayush", googlePlaces.toString());
                URLConnection tc = googlePlaces.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        tc.getInputStream()));
                String line;
                String sb = "";
                //take Google's legible JSON and turn it into one big string.
                while ((line = in.readLine()) != null) {
                    sb+=line;
                }
                //turn that string into a JSON object
                JSONObject predictions = new JSONObject(sb);
                //now get the JSON array that's inside that object
                JSONObject ja = new JSONObject(predictions.getString("result"));

                JSONObject jb = new JSONObject(ja.getString("geometry"));

                JSONObject jc = new JSONObject(jb.getString("location"));

                double lat=jc.getDouble("lat");

                double lng=jc.getDouble("lng");
                loc2 = new LatLng(lat, lng);

            } catch (IOException e) {
                Log.e("YourApp", "GetPlaces : doInBackground", e);
            } catch (JSONException e) {
                Log.e("YourApp", "GetPlaces : doInBackground", e);
            }
            return loc2;
        }
        protected void onPostExecute(LatLng result) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(result));
        }
    }

    private class GetPlaces extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        // three dots is java for an array of strings
        protected ArrayList<String> doInBackground(String... arg0) {

            ArrayList<String> predictionsArr = new ArrayList<String>();
            try {

                URL googlePlaces = new URL(
                        "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+ URLEncoder.encode(arg0[0], "UTF-8") +"&sensor=true&key=AIzaSyAaliOe90JD33vEL3pq4QHYSvgXgW0PKeM");
                URLConnection tc = googlePlaces.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        tc.getInputStream()));

                String line;
                String sb = "";
                //take Google's legible JSON and turn it into one big string.
                while ((line = in.readLine()) != null) {
                    sb+=line;
                }
                //turn that string into a JSON object
                JSONObject predictions = new JSONObject(sb);
                //now get the JSON array that's inside that object
                JSONArray ja = new JSONArray(predictions.getString("predictions"));

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    //add each entry to our array
                    predictionsArr.add(jo.getString("description"));

                    refArr.add(jo.getString("reference"));
                }

            } catch (IOException e) {
                Log.e("YourApp", "GetPlaces : doInBackground", e);
            } catch (JSONException e) {
                Log.e("YourApp", "GetPlaces : doInBackground", e);
            }
            return predictionsArr;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {

            Log.d("YourApp", "onPostExecute : " + result.size());
            //update the adapter
            adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.item_list);
            adapter.setNotifyOnChange(true);
            //attach the adapter to textview
            textView.setAdapter(adapter);

            for (String string : result)  {

                Log.d("YourApp", "onPostExecute : result = " + string);
                adapter.add(string);
                adapter.notifyDataSetChanged();
            }
            Log.d("YourApp", "onPostExecute : autoCompleteAdapter" + adapter.getCount());
        }
    }

    private class CommentsInitialise extends AsyncTask<Long, Void, CommentIds> {


        @Override
        protected CommentIds doInBackground(Long... longs) {

            Commentidsendpoint.Builder builder = new Commentidsendpoint.Builder(
                    AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
            builder = CloudEndpointUtils.updateBuilder(builder);
            Commentidsendpoint commentidsendpoint = builder.build();
            CommentIds commentIds = new CommentIds();
            commentIds.setId(longs[0]);
            commentIds.setCommentsIds(new ArrayList<Long>());
            try {
                return commentidsendpoint.insertCommentIds(commentIds).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return commentIds;
        }

        @Override
        protected void onPostExecute(CommentIds commentIds) {

            Log.d("Ayush", "HEHE !" + commentIds.getCommentsIds() +" "+ commentIds.getId());
        }
    }

    private class ParentInsert extends AsyncTask<Reports, Void, Reports> {

        @Override
        protected Reports doInBackground(Reports... parent) {

            Reportsendpoint.Builder builder = new Reportsendpoint.Builder(
                    AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
            builder = CloudEndpointUtils.updateBuilder(builder);
            Reportsendpoint endpoint = builder.build();
            try {
                return endpoint.insertReports(parent[0]).execute();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("Ayush","MAJOR SHIZ"+e.getLocalizedMessage());
                new ParentInsert().execute(parent[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Reports result) {

            if (result == null) return;
            Log.v("Ayush", ""+result.getId());

            if(result.getId() != null) {

                new CommentsInitialise().execute(result.getId());
                List<Reports> temp = new ArrayList<Reports>(1);
                temp.add(result);
                addMarker(temp);
                Toast.makeText(superContext, "Report submitted !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CommentRetriever extends AsyncTask<Long, Void, List<Comments>> {


        @Override
        protected List<Comments> doInBackground(Long... longs) {

            Commentsendpoint.Builder endpointBuilder = new Commentsendpoint.Builder(AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
            endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
            Commentsendpoint commentsendpoint = endpointBuilder.build();

            List<Comments> comments = new ArrayList<Comments>();

            Log.d("Ayush", "gg"+longs.length);

            for(int i=0; i<longs.length-1; i++) {

                try {
                    Log.d("Ayush", "ID=" + longs[i]);
                    comments.add(commentsendpoint.getComments(longs[i]).execute());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return comments;
        }

        @Override
        protected void onPostExecute(List<Comments> comments) {

            listDataChild.clear();
            List<Comments> topComments = new ArrayList<Comments>();
            for(Comments comment : comments) {

                if(comment.getTups() > 10) topComments.add(comment);
            }
            listDataChild.put(listDataHeader.get(1), topComments);
            listDataChild.put(listDataHeader.get(0), comments);
            listAdapter = new ExpandableListAdapter(reportContext, listDataHeader, listDataChild);
            listOfComments.setAdapter(listAdapter);
            listOfComments.invalidate();
        }
    }

    private class ListOfCommentIdsAsyncRetriever extends AsyncTask<Long, Void, CommentIds> {


        @Override
        protected CommentIds doInBackground(Long... longs) {

            Commentidsendpoint.Builder endpointBuilder = new Commentidsendpoint.Builder(AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
            endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
            Commentidsendpoint commentidsendpoint = endpointBuilder.build();
            try {
                return commentidsendpoint.getCommentIds(longs[0]).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(CommentIds result) {

            if(result == null || result.getCommentsIds()==null || result.getCommentsIds().size()==0) return;
            int size = result.getCommentsIds().size();
            Long [] toSend = new Long[size];
            for(int i=0; i<size; i++) {

                toSend[i] = result.getCommentsIds().get(i);
            }
            new CommentRetriever().execute(toSend);
        }
    }

    private class AddComment extends AsyncTask<Object, Void, Void> {


        @Override
        protected Void doInBackground(Object... objects) {

            Long id = (Long)objects[0];
            String comment = (String)objects[1];
            Long idToAdd = null;

            Commentsendpoint.Builder endpointBuilder1 = new Commentsendpoint.Builder(AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
            endpointBuilder1 = CloudEndpointUtils.updateBuilder(endpointBuilder1);
            Commentsendpoint commentsendpoint = endpointBuilder1.build();

            Comments comments = new Comments();
            comments.setFlags(0);
            comments.setDos(new DateTime(new Date()));
            comments.setTdwns(0);
            comments.setTups(0);
            comments.setData(Arrays.asList("Ayush", comment));

            try {
                idToAdd = (commentsendpoint.insertComments(comments).execute()).getId();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Commentidsendpoint.Builder endpointBuilder2 = new Commentidsendpoint.Builder(AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
            endpointBuilder2 = CloudEndpointUtils.updateBuilder(endpointBuilder2);
            Commentidsendpoint commentidsendpoint = endpointBuilder2.build();

            try {
                CommentIds ids = commentidsendpoint.getCommentIds(id).execute();
                List<Long> listIds = ids.getCommentsIds();
                if(listIds == null || listIds.size()==0) listIds = new ArrayList<Long>();
                listIds.add(idToAdd);
                ids.setCommentsIds(listIds);
                commentidsendpoint.updateCommentIds(ids).execute();
            } catch (IOException e) {
                Log.e("Ayush", e.getLocalizedMessage());
            }

            return null;
        }
    }

    private class ListOfParentsAsyncRetriever extends AsyncTask<Void, Void, CollectionResponseReports> {

        @Override
        protected CollectionResponseReports doInBackground(Void... params) {


            Reportsendpoint.Builder endpointBuilder = new Reportsendpoint.Builder(
                    AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);

            endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

            CollectionResponseReports result;

            Reportsendpoint endpoint = endpointBuilder.build();

            try {
                result = endpoint.listReports().execute();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("Ayush", e.getLocalizedMessage());
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(CollectionResponseReports result) {

            if(result == null || result.size()==0) return;

            Log.v("Ayush", "Server respose "+result.size());

            List<Reports> Reff = result.getItems();

            if(Reff == null || Reff.size()==0) return;

            Log.v("Ayush", "Server respose "+Reff.size());

            parents = Reff;
            mProgress.setVisibility(View.GONE);
            addMarker(parents);
        }
    }
}