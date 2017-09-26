package br.com.cuiateam.ajudame;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.cuiateam.ajudame.ong.OngItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class OngFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static final String API_URL = "http://10.0.2.2:3003/api/ong/"; //localhost
    private static List<OngItem> ITEMS = new ArrayList<>();
    private static MyOngRecyclerViewAdapter adapter;
    public static RecyclerView recyclerView;

    public OngFragment() {}

    public static OngFragment newInstance(int columnCount) {
        OngFragment fragment = new OngFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setRetainInstance(true);
        fragment.setArguments(args);
        return fragment;
    }

    public void refreshData(OngItem[] data) {
        ITEMS = Arrays.asList(data);
        adapter = new MyOngRecyclerViewAdapter(ITEMS, mListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        new RetrieveFeedTask().execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ong_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            adapter = new MyOngRecyclerViewAdapter(ITEMS, mListener);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(OngItem item);
    }

    private class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        protected void onPreExecute() {
            ITEMS.clear();
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("AJUDA-ME", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                Toast.makeText(getActivity(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }else{
                Gson gson = new Gson();
                OngItem[] ongItems = gson.fromJson(response, OngItem[].class);
                ITEMS = Arrays.asList(ongItems);
                OngFragment ongFragment = new OngFragment();
                ongFragment.refreshData(ongItems);
            }
        }
    }
}