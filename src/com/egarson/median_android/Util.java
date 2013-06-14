package com.egarson.median_android;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;

// Misc static funcs/decls
public class Util {
	
	public final static String MAIN = "com.egarson.median_android";
	
	// Parse an integer from the given resource parented by the given Activity
	public static int intValue(Activity activity, int resourceId) {
		EditText editText = (EditText) activity.findViewById(resourceId);
		if (editText != null) {
			String value = editText.getText().toString();
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				Log.w(MAIN, "Not an integer: " + value);
			}			
		} else {
			Log.e(MAIN, "Widget not found with id: " + resourceId);
		}
		return 0;
	}

	// Parse a string from the given resource parented by the given Activity
	public static String strValue(Activity activity, int resourceId) {
		EditText editText = (EditText) activity.findViewById(resourceId);
		if (editText != null) {
			return editText.getText().toString();
		} else {
			Log.e(MAIN, "Widget not found with id: " + resourceId);
		}
		return ""; // Prefer the empty string to null: caveat emptor
	}

	public static int statusCode(HttpResponse response) {
		return response.getStatusLine().getStatusCode();
	}

	public static String requestJSON(Uri uri) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(new HttpGet(uri.toString()));
			if (statusCode(response) == 200) {
				HttpEntity entity = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MAIN, "Failed to retrieve response");
			}
		} catch (Exception e) {
			Log.e(MAIN, e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		return builder.toString();
	}
}
