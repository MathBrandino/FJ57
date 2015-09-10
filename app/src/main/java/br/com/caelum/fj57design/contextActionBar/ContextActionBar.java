package br.com.caelum.fj57design.contextActionBar;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by matheus on 09/09/15.
 */
public class ContextActionBar implements android.support.v7.view.ActionMode.Callback {


    @Override
    public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {

        menu.add("Deletar");
        return false;
    }

    @Override
    public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {

    }
}
