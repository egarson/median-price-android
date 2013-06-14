package com.egarson.median_android;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import static com.egarson.median_android.Util.MAIN;

public class RequestMedianTask extends AsyncTask<Uri, Void, String> {

	@Override
	protected String doInBackground(Uri... uri) {
		return Util.requestJSON(uri[0]);
    }

    protected void onPostExecute(Long result) {
    	Log.d(MAIN, "Request complete");
    }
}
