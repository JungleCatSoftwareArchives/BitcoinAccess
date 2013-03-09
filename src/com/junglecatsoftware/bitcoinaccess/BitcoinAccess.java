/*
* Copyright 2013 Bryan Wyatt
*
* This file is part of BitcoinAccess.
*
* BitcoinAccess is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* BitcoinAccess is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with BitcoinAccess. If not, see <http://www.gnu.org/licenses/>.
*/

package com.junglecatsoftware.bitcoinaccess;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.annotation.SuppressLint;
import android.content.Context;

public class BitcoinAccess extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager mViewPager;
	private ViewPagerAdapter mViewPagerAdapter;
	private ActionBar actionBar;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.bitcoinaccess);

        // Set up the action bar.
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
    	setUpView();
    	setTab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
    
    private void setUpView(){
    	mViewPager = (ViewPager) findViewById(R.id.viewPager);
    	mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager());
    	mViewPager.setAdapter(mViewPagerAdapter);
    	
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mViewPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(mViewPagerAdapter.getPageTitle(i)).setTabListener(this));
        }
    	
    	mViewPager.setCurrentItem(0);
    	actionBar.setSelectedNavigationItem(0);
    }
    
    private void setTab(){
    	mViewPager.setOnPageChangeListener(new OnPageChangeListener(){
    		@Override
    		public void onPageScrollStateChanged(int position){
    		}
    		@Override
    		public void onPageScrolled(int arg0, float arg1, int arg2){
    		}
    		@Override
    		public void onPageSelected(int position){
    			actionBar.setSelectedNavigationItem(position);
    		}
    	});
    }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	public class ViewPagerAdapter extends FragmentPagerAdapter {
		private Context context;
		
        public ViewPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int tab) {
    		Fragment f = new Fragment();
    		switch(tab){
    			case 0:
    				f=MainTab.newInstance(context);
    				break;
    			case 1:
    				f=TxTab.newInstance(context);
    				break;
    			case 2:
    				f=OptTab.newInstance(context);
    				break;
    		}
    		
    		return f;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @SuppressLint("DefaultLocale") @Override
        public CharSequence getPageTitle(int position) {
	        switch (position) {
	            case 0:
	                return getString(R.string.Main_Tab).toUpperCase();
	            case 1:
	                return getString(R.string.TX_Tab).toUpperCase();
	            case 2:
	                return getString(R.string.Opt_Tab).toUpperCase();
	        }
	        return null;
        }
    }
}
