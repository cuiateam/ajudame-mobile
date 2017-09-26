package br.com.cuiateam.ajudame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.cuiateam.ajudame.ong.OngItem;

public class MainActivity extends AppCompatActivity implements OngFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onListFragmentInteraction(OngItem item) {

    }
}
