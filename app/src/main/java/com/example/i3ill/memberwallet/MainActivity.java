package com.example.i3ill.memberwallet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    MemberDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View v) {
        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.btMem:
                i = new Intent(this, ListMember.class);
                startActivity(i);
                break;

            case R.id.btAdd:
                i = new Intent(this, AddMember.class);
                startActivityForResult(i, 88);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {

                String name = data.getStringExtra("name");
                String id = data.getStringExtra("id");
                String owner = data.getStringExtra("owner");
                String tel = data.getStringExtra("tel");
                String from = data.getStringExtra("from");
                String thru = data.getStringExtra("thru");

                helper = new MemberDB(this);
                // Add temporary data
                SQLiteDatabase dbw = helper.getWritableDatabase();
                ContentValues ri = new ContentValues();
                ri.put("memName", name);
                ri.put("memID", id);
                ri.put("memOwner", owner);
                ri.put("memTel", tel);
                ri.put("validFrom", from);
                ri.put("validThru", thru);
                //ri.put("image", pic);
                long new_id = dbw.insert("member", null, ri);
                dbw.close();
            }
        }
        Log.d("member", "onActivityResult");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
