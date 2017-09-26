package br.com.cuiateam.ajudame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.cuiateam.ajudame.ong.OngItem;

public class MainActivity extends AppCompatActivity implements OngFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getString(R.string.organizations));

    }

    @Override
    public void onListFragmentInteraction(OngItem item) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_about:
                Intent intent = new Intent(this, AboutActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.menu_refresh:
                new OngFragment.RetrieveFeedTask().execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
