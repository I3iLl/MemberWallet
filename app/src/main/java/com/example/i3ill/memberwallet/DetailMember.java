package com.example.i3ill.memberwallet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailMember extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_member);

        Intent i = this.getIntent();
        int tmp = i.getIntExtra("chosen", 0);

        helper = new MemberDB(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                //"SELECT _id, code , (grade  || ' (' || credit || ' Credits)') AS B FROM course;",
                "SELECT * FROM member WHERE _id="+ tmp +";",
                null);

        cursor.moveToFirst();
        String name = cursor.getString(1);
        String id = cursor.getString(2);
        String owner = cursor.getString(3);
        String tel = cursor.getString(4);
        String from = cursor.getString(5);
        String thru = cursor.getString(6);

        TextView tvName = (TextView)findViewById(R.id.tvName);
        TextView tvID = (TextView)findViewById(R.id.tvID);
        TextView tvOwner = (TextView)findViewById(R.id.tvOwner);
        TextView tvTel = (TextView)findViewById(R.id.tvTel);
        TextView tvFrom = (TextView)findViewById(R.id.tvFrom);
        TextView tvThru = (TextView)findViewById(R.id.tvThru);

        tvName.setText(name);
        tvID.setText(id);
        tvOwner.setText(owner);
        tvTel.setText(tel);
        tvFrom.setText(from);
        tvThru.setText(thru);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_member, menu);
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
