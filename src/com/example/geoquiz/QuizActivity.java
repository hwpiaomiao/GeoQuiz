package com.example.geoquiz;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPreButton;
	//private TextView mQuestionTextView;
	private int mCurrentIndex = 0;
	
	private TextView mTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse[] { 
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false), 
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true), 
			new TrueFalse(R.string.question_asia, true), 
			};

	private void updataQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		//mQuestionTextView.setText(question);
		mTextView.setText(question);
	}

	private void checkAnswer(boolean usePressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

		int messageResId = 0;
		if (usePressedTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView=(TextView) findViewById(R.id.question_text_view);
		mTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updataQuestion();
				
			}
		});
		

		//mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		mTrueButton = (Button) findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});
		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});

		mNextButton = (ImageButton) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updataQuestion();
			}
		});
		
		mPreButton=(ImageButton) findViewById(R.id.pre_button);
		mPreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mCurrentIndex==0) {
					mCurrentIndex=mQuestionBank.length-1;
					
				}else {
					mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
				}
				updataQuestion();
			}
		});
		updataQuestion();

	}

}
