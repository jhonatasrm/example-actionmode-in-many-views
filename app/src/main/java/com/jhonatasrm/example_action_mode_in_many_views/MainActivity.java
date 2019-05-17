package com.jhonatasrm.example_action_mode_in_many_views;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class MainActivity extends ListActivity implements AbsListView.MultiChoiceModeListener {

    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<String> selected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        // criado o ArrayAdapter e após isso adicionando elementos ao Array
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter.add("Apple");
        adapter.add("Avocado");
        adapter.add("Banana");
        adapter.add("Pineapple");
        adapter.add("Grape");
        adapter.add("Pumpkin");
        adapter.add("Apple 2”);
        adapter.add("Avocado 2”);
        adapter.add("Banana 2”);
        adapter.add("Pineapple 2”);
        adapter.add("Grape 2”);
        adapter.add("Pumpkin 2”);

        // definindo a lista de adapter sendo o adapter criado
        setListAdapter(adapter);
        listView = getListView();
        listView.setMultiChoiceModeListener(this);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    // verifica a mudança de estado de um item
    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
        String s = adapter.getItem(position);
        View view = listView.getChildAt(position);

        if(checked){
            view.setBackgroundColor(Color.GRAY);
            selected.add(s);
        }else{
            view.setBackgroundColor(Color.TRANSPARENT);
            selected.remove(s);
        }
    }

    // chama o método inflater para apresentar as opções de menu criadas previamente
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_action, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    // verifica qual item foi clicado comparando o seu id
    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.delete){
            for(String s : selected){
                adapter.remove(s);
            }
            actionMode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        int count = listView.getChildCount();

        for(int i = 0; i < count; i++){
            View view = listView.getChildAt(i);
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        selected.clear();
    }
}
