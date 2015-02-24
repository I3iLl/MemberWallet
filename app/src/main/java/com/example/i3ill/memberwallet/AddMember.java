package com.example.i3ill.memberwallet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class AddMember extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
    }

    public void camClicked(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void addClicked(View v) {
        EditText etName = (EditText)findViewById(R.id.etName);
        EditText etId = (EditText)findViewById(R.id.etID);
        EditText etOwner = (EditText)findViewById(R.id.etOwner);
        EditText etTel = (EditText)findViewById(R.id.etTel);
        EditText etFrom = (EditText)findViewById(R.id.etFrom);
        EditText etThru = (EditText)findViewById(R.id.etThru);


        String sName = etName.getText().toString();
        String sID = etId.getText().toString();
        String sOwner = etOwner.getText().toString();
        String sTel = etTel.getText().toString();
        String sFrom = etFrom.getText().toString();
        String sThru = etThru.getText().toString();

        if (sName.trim().length() == 0 || sID.trim().length() == 0 || sOwner.trim().length() == 0 || sTel.trim().length() == 0 || sFrom.trim().length() == 0 || sThru.trim().length() == 0) {
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Please fill all the item",
                    Toast.LENGTH_SHORT);
            t.show();
        }
        else {

            Intent result = new Intent();
            result.putExtra("name", sName);
            result.putExtra("id", sID);
            result.putExtra("owner", sOwner);
            result.putExtra("tel", sTel);
            result.putExtra("from", sFrom);
            result.putExtra("thru", sThru);
            result.putExtra("image", imageBitmap);
            this.setResult(RESULT_OK, result);
            this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) findViewById(R.id.image);
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_member, menu);
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
