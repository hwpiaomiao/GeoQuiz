package com.example.geoquiz;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.net.rtp.RtpStream;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity{
	
	public static final String EXTRA_ANSWER_IS_TRUE="com.example.geoquiz.answer_is_true";

	public static final String EXTRA_ANSWER_SHOWN="com.example.geoquiz.answer_shown";
	private boolean mAnswerIsTrue;
	private TextView mAnswetTextView;
	private Button mShowAnswer;
	private void setAnswerShownResult(boolean inAnswerShown) {
		Intent data=new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN,inAnswerShown);
		setResult(RESULT_OK, data);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setAnswerShownResult(false);
		setContentView(R.layout.activity_cheat);
		mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAnswetTextView=(TextView) findViewById(R.id.answerTextView);
		mShowAnswer=(Button) findViewById(R.id.showAnswerButton);
				mShowAnswer.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (mAnswerIsTrue) {
							mAnswetTextView.setText(R.string.true_button);
						}else {
							mAnswetTextView.setText(R.string.false_button);
						}
						setAnswerShownResult(true);
					}
				});
	}
}
