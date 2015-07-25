package com.example.tabsexample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private ActionBar actionBar;
	
	static final String TWITTER_CALLBACK_URL = "x-oauthflow-twitter://callback";
	static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
	static final String CONSUMER_KEY = "BKJ5DkkfzDIS5ERJMVMfikCyv";
	static final String CONSUMER_SECRET = "LuNpJSUipBw39gIP8HHEvS0DZ5dn3UT5uDn3S9u15JXrtoeJ0d";
	static String AUTH_TOKEN = "";
	static String AUTH_TOKEN_SECRET = "";
	
	private static Twitter twitter;
	private static RequestToken requestToken;
	private static AccessToken accessToken;
	
	private static Fragment first = new FirstFragment();
	private static Fragment second = new SecondFragment();
	private static Fragment third = new ThirdFragment();
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
   
        if(twitter==null) {
        	twitter = new TwitterFactory().getInstance();        	
        }
        if(accessToken==null) {
            loginToTwitter();        	
        }
        
        actionBar= getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        Tab tab1 = actionBar.newTab();
        tab1.setText("ListView");
        tab1.setTabListener(new MyTabListener(first));
        
        Tab tab2 = actionBar.newTab();
        tab2.setText("Images");
        tab2.setTabListener(new MyTabListener(second));
        
        Tab tab3 = actionBar.newTab();
        tab3.setText("Tweets");
        tab3.setTabListener(new MyTabListener(third));
        
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }

    /**
     * Method for logging in to Twitter
     */
    private void loginToTwitter() {
        Executor exe=new Executor() {
            public void execute(Runnable command) { 
                try {                         
                    try {
                        twitter.setOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
                        requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
                        MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
                    } 
                    catch (Exception e) {
                        Log.d("SN",e.getMessage());
                    } 
                }
                catch(Exception ex) {
                    Log.d("Exception:",ex.getMessage());
                    
                }
            }
        };
        exe.execute(null);
    }

	/*
	 *	Called after authenticating with Acess Token from the Twitter
	 */
	protected void onNewIntent(Intent intent) {
		Uri uri = intent.getData();
        if(uri!=null && uri.toString().startsWith(TWITTER_CALLBACK_URL)) {
        	final String verifier = uri.getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);
        	try {
        		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            	AUTH_TOKEN = accessToken.getToken();
            	AUTH_TOKEN_SECRET = accessToken.getTokenSecret();
        	}
        	catch(Exception ex) {
        		Log.d("SN",ex.getMessage());
        	}
    	super.onNewIntent(intent);
        }
    }
    
	
	/* 
	 *	Method for linking menu xml file
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
     *	Method handling the menu actions
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        int id = item.getItemId();
        switch(id) {
        case R.id.action_about :
        	Toast.makeText(this, "A simple Android App", Toast.LENGTH_LONG).show();
        	break;
        case R.id.action_exit :
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    
	/**
	 * Method for fetching tweets from Twitter
	 * 
	 * @param search
	 * @return List<String>
	 */
	public static List<String> getTweets(String search) {

		ArrayList<String> tweetString=new ArrayList<String>();
		try {
			Query query = new Query(search);
			List<Status> list = twitter.search(query).getTweets();
			for (int i=0;i<list.size();i++) {
				twitter4j.Status each = (twitter4j.Status) list.get(i);
				tweetString.add(each.getUser().getName()+":"+"\n"+each.getText());
			}
		}
		catch(Exception ex) {
			Log.d("SN", ex.getMessage());
		}
		return tweetString;
	}
}
