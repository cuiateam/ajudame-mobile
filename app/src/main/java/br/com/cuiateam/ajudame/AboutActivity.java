package br.com.cuiateam.ajudame;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle(getString(R.string.about));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView view = (WebView) findViewById(R.id.About_WebView);

        String text = "<html><head></head><body style='text-align:justify;color:gray;background-color:white;font-size: 1.1em;'>";
        text += "<p><b>" + getString(R.string.app_name)+"</b> " + getString(R.string.txtAbout);
        text += "</p></body></html>";


        view.loadData(text,"text/html; charset=utf-8","UTF-8");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent browserIntent;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_web_site:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.webSiteUrl)));
                startActivity(browserIntent);
                return true;
            case R.id.menu_github:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.githubUrl)));
                startActivity(browserIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
