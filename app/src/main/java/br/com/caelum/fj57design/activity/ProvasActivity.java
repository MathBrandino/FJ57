package br.com.caelum.fj57design.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.fragments.DetalhesFragment;
import br.com.caelum.fj57design.fragments.ProvasFragment;

/**
 * Created by matheus on 14/09/15.
 */
public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ProvasFragment fragment = new ProvasFragment();
        transaction.replace(R.id.frame_provas, fragment);

        if (isTablet()) {
            transaction.replace(R.id.frame_provas_detalhada, new DetalhesFragment());
        }

        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }
}