package com.example.igift;

import java.io.IOException;

import org.apache.http.HttpRequest;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends YouTubeBaseActivity implements
YouTubePlayer.OnInitializedListener {

	static private final String DEVELOPER_KEY = "AIzaSyD-uCz7cy__XPDiEyKwsI1yhWEQYyIR28k";       
	static private final String VIDEO = "i4X8U8p8fes";
	static MainActivity reference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		reference = this;
		YouTubePlayerView youTubeView = (YouTubePlayerView)findViewById(R.id.youtube_view);   
		
		youTubeView.initialize(DEVELOPER_KEY, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult error) {
		// TODO Auto-generated method stub
		 Toast.makeText(this, "Oh no! "+error.toString(),
				 Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean arg2) {
		// TODO Auto-generated method stub
		
		player.loadVideo(VIDEO);
		
	}
	
	public void searchYoutube(View view) {
		
		String getURL = "https://gdata.youtube.com/feeds/api/videos?";
		String searchTerm = new String();
		EditText value = (EditText) MainActivity.reference.findViewById(R.id.editText1);
		String testURL ="https://www.youtube.com/results?search_query=";
		searchTerm = value.getText().toString();
		
		searchTerm= searchTerm.replace(" ", "%20");
		
		getURL = getURL+searchTerm;
		
		
		 
	/*	Intent intent = new Intent(Intent.ACTION_SEARCH);
		intent.setPackage("com.google.android.youtube");
		intent.putExtra("query", testURL+searchTerm);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);*/
		
		final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
		final AndroidJsonFactory JSON_FACTORY = new AndroidJsonFactory();
	//	AndroidJsonFactory jsonfactory = new AndroidJsonFactory();
		
		
		YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT , JSON_FACTORY , new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }

			@Override
			public void initialize(com.google.api.client.http.HttpRequest arg0)
					throws IOException {
				// TODO Auto-generated method stub
				
			}
        }).setApplicationName("IGiftSearchRsult").build();
        
		YouTube.Search.List search;
		try {
			search = youtube.search().list("id,snippet");
			search.setKey(DEVELOPER_KEY);
	        search.setQ("dogs");
	        search.setMaxResults((long) 25);
	        System.out.println(search);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
      //  SearchListResponse searchResponse = search.
        
        
		
	}


	
}
