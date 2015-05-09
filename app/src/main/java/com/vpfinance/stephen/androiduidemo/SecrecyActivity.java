package com.vpfinance.stephen.androiduidemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.stackchina.lib.gesturelock.LockPatternView;

import java.util.List;

public class SecrecyActivity extends FragmentActivity
{

	private LockPatternView mLockPatternView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_secrecy);
		
		mLockPatternView = (LockPatternView) findViewById(R.id.lockPattern);
		mLockPatternView.setOnPatternListener(new LockPatternView.OnPatternListener() {

			@Override
			public void onPatternStart()
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onPatternCleared()
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onPatternCellAdded(List<LockPatternView.Cell> pattern)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onPatternDetected(List<LockPatternView.Cell> pattern)
			{
				if (pattern.size() >= 4) {
					
				}
			}
			
		});

	}

}
