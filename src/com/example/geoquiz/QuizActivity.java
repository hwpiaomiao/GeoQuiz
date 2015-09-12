package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private boolean mIsCheater;
	private Button mCheatButton;
	private static final String KEY_INDEX = "index";
	private static final String KEY_IS_CHEATER="cheater";
	private Button mTrueButton;;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPreButton;
	private int mCurrentIndex = 0;

	private TextView mTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse[] { 
			new TrueFalse(R.string.question_1, true),
			new TrueFalse(R.string.question_2, false),
			new TrueFalse(R.string.question_3, false),
			new TrueFalse(R.string.question_4, true),
			new TrueFalse(R.string.question_5, true), };

	private void updataQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mTextView.setText(question);
	}

	private void checkAnswer(boolean usePressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

		int messageResId = 0;
		if (mIsCheater) {
			messageResId = R.string.judgment_toast;
		} else {
			if (usePressedTrue == answerIsTrue) {
				messageResId = R.string.correct_toast;
			} else {
				messageResId = R.string.incorrect_toast;
			}
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_INDEX, mCurrentIndex);
		outState.putBoolean(KEY_IS_CHEATER, mIsCheater);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				startActivityForResult(i, 0);
			}
		});
		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
			mIsCheater=savedInstanceState.getBoolean(KEY_IS_CHEATER, false);

		}
		mTextView = (TextView) findViewById(R.id.question_text_view);
		mTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updataQuestion();

			}
		});

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
				mIsCheater=false;
				updataQuestion();
				
				
			}
		});

		mPreButton = (ImageButton) findViewById(R.id.pre_button);
		mPreButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCurrentIndex == 0) {
					mCurrentIndex = mQuestionBank.length - 1;

				} else {
					mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
				}
				updataQuestion();
			}
		});
		updataQuestion();
	}

}
