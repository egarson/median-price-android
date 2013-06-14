package com.egarson.median_android;

import static com.egarson.median_android.Util.MAIN;
import static com.egarson.median_android.Util.strValue;

import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	//private static final String BASE_URI = "http://192.168.0.100:3000/items/median.json";
	//private static final String BASE_URI = "http://0.0.0.0:3000/items/median.json";
	//private static final String BASE_URI = "http://184.161.147.195:3000/items/median.json";
	private static final String BASE_URI = "http://median-price.herokuapp.com/items/median.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void btnGetMedianClick(View view) {
		try {
			// TODO start progress bar			
			AsyncTask<Uri, Void, String> task = new RequestMedianTask().execute(buildUri());

			JSONObject json = new JSONObject(task.get());
			String median = json.getString("median");

			TextView txtMedian = (TextView) findViewById(R.id.txtMedian);
			txtMedian.setText(median);

		} catch (Exception e) {
			Log.e(MAIN, e.getClass().getSimpleName() + ": " + e.toString());
		} finally {
			// TODO stop progress bar
		}
	}

	private Uri buildUri() {
		String brand = strValue(this, R.id.edtBrand);
		String yearFrom = strValue(this, R.id.edtYearFrom);
		String yearTo = strValue(this, R.id.edtYearTo);
		return Uri.parse(BASE_URI + "?brand=" + brand + "&year=" + yearFrom + "-" + yearTo);
	}
}
