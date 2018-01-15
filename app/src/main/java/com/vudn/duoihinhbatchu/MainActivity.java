package com.vudn.duoihinhbatchu;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TooManyListenersException;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int MSG_RESET_QUESTION = 0;
    private TextView txtHeart;
    private TextView txtCoin;
    private TextView txtMessage;
    private ImageView imgPiture;

    private List<Button> buttonsAnswer;
    private List<Button> buttonsKeyWord;
    private Button btnNext;
    private Button btnKeyword;

    private List<Question> questions;
    private Question question;

    private List<String> chars;

    private int questionNumber;
    private int lengthQuestion;
    private int answerNumber;
    private int heart;
    private int coin;

    private boolean flagEnabled;
    private String answer;
    private String trueAnswer;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        registerListeners();
        addQuestion();
        creatQuestion();
    }

    private void initializeComponents() {
        buttonsAnswer = new ArrayList<>();
        buttonsKeyWord = new ArrayList<>();
        heart = 5;
        flagEnabled = true;
        txtHeart = (TextView) findViewById(R.id.txt_heart);
        txtHeart.setText(String.valueOf(heart));
        txtCoin = (TextView) findViewById(R.id.txt_coin);
        txtCoin.setText(String.valueOf(coin));
        txtMessage = (TextView) findViewById(R.id.txt_message);
        txtMessage.setText("");
        imgPiture = (ImageView) findViewById(R.id.img_piture);
        btnNext = (Button) findViewById(R.id.btn_next_question);

        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_1));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_2));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_3));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_4));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_5));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_6));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_7));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_8));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_9));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_10));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_11));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_12));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_13));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_14));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_15));
        buttonsAnswer.add((Button) findViewById(R.id.btn_answer_16));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_1));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_2));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_3));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_4));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_5));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_6));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_7));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_8));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_9));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_10));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_11));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_12));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_13));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_14));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_15));
        buttonsKeyWord.add((Button) findViewById(R.id.btn_keyword_16));
    }

    private void registerListeners() {
        btnNext.setOnClickListener(this);
        for (int i = 0; i < buttonsKeyWord.size(); i++) {
            buttonsKeyWord.get(i).setOnClickListener(this);
        }
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_RESET_QUESTION:
                        resetQuestion();
                    break;

                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_question:
                nextQuestion();
                break;

            default:
                break;
        }
        if (flagEnabled == true) {
            switch (v.getId()) {
                case R.id.btn_keyword_1:
                case R.id.btn_keyword_2:
                case R.id.btn_keyword_3:
                case R.id.btn_keyword_4:
                case R.id.btn_keyword_5:
                case R.id.btn_keyword_6:
                case R.id.btn_keyword_7:
                case R.id.btn_keyword_8:
                case R.id.btn_keyword_9:
                case R.id.btn_keyword_10:
                case R.id.btn_keyword_11:
                case R.id.btn_keyword_12:
                case R.id.btn_keyword_13:
                case R.id.btn_keyword_14:
                case R.id.btn_keyword_15:
                case R.id.btn_keyword_16:
                    checkKeyword(v);

                default:
                    break;
            }
        }
    }

    private void addQuestion() {
        questions = new ArrayList<>();
        questions.add(new Question(R.drawable.aomua, "aomua"));
        questions.add(new Question(R.drawable.baocao, "baocao"));
        questions.add(new Question(R.drawable.canthiep, "canthiep"));
        questions.add(new Question(R.drawable.cattuong, "cattuong"));
        questions.add(new Question(R.drawable.chieutre, "chieutre"));
        questions.add(new Question(R.drawable.danhlua, "danhlua"));
        questions.add(new Question(R.drawable.danong, "danong"));
        questions.add(new Question(R.drawable.giandiep, "giandiep"));
        questions.add(new Question(R.drawable.giangmai, "giangmai"));
        questions.add(new Question(R.drawable.hoidong, "hoidong"));
        questions.add(new Question(R.drawable.hongtam, "hongtam"));
        questions.add(new Question(R.drawable.khoailang, "khoailang"));
        questions.add(new Question(R.drawable.kiemchuyen, "kiemchuyen"));
        questions.add(new Question(R.drawable.lancan, "lancan"));
        questions.add(new Question(R.drawable.masat, "masat"));
        questions.add(new Question(R.drawable.nambancau, "nambancau"));
        questions.add(new Question(R.drawable.oto, "oto"));
        questions.add(new Question(R.drawable.quyhang, "quyhang"));
        questions.add(new Question(R.drawable.songsong, "songsong"));
        questions.add(new Question(R.drawable.thattinh, "thattinh"));
        questions.add(new Question(R.drawable.thothe, "thothe"));
        questions.add(new Question(R.drawable.tichphan, "tichphan"));
        questions.add(new Question(R.drawable.tohoai, "tohoai"));
        questions.add(new Question(R.drawable.totien, "totien"));
        questions.add(new Question(R.drawable.tranhthu, "tranhthu"));
        questions.add(new Question(R.drawable.vuaphaluoi, "vuaphaluoi"));
        questions.add(new Question(R.drawable.vuonbachthu, "vuonbachthu"));
        questions.add(new Question(R.drawable.xakep, "xakep"));
        questions.add(new Question(R.drawable.xaphong, "xaphong"));
        questions.add(new Question(R.drawable.xedapdien, "xedapdien"));
        Collections.shuffle(questions);
    }

    private void creatQuestion() {
        question = questions.get(questionNumber);
        imgPiture.setBackgroundResource(question.getId());
        trueAnswer = question.getKeyword();
        lengthQuestion = question.getKeyword().length();
        chars = question.getChars();
        for (int i = 0; i < lengthQuestion; i++) {
            buttonsAnswer.get(i).setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < buttonsKeyWord.size(); i++) {
            buttonsKeyWord.get(i).setText(chars.get(i));
        }
        answer = "";
        answerNumber = 0;
        flagEnabled = true;
    }

    private void nextQuestion() {
        if (questionNumber < questions.size() - 1) {
            questionNumber++;
            resetButton();
            creatQuestion();
        } else {
            Toast.makeText(this,
                    "You win",
                    Toast.LENGTH_SHORT).show();
        }
        btnNext.setEnabled(false);
    }

    private void checkKeyword(View view) {
        btnKeyword = (Button) view;
        btnKeyword.setVisibility(View.INVISIBLE);
        buttonsAnswer.get(answerNumber).setText(btnKeyword.getText());
        answer += btnKeyword.getText();
        answerNumber++;
        if (answerNumber == lengthQuestion) {
            flagEnabled = false;
            checkAnswer();
        }
    }

    private void checkAnswer() {
        if (trueAnswer.equals(answer)) {
            coin += 100;
            txtCoin.setText(String.valueOf(coin));
            txtMessage.setText("Bạn đã trả lời đúng");
            btnNext.setEnabled(true);
            for (int i = 0; i < lengthQuestion; i++) {
                buttonsAnswer.get(i).setBackgroundResource(R.drawable.ic_tile_true);
            }
        } else {
            txtMessage.setText("Bạn đã trả lời sai");
            for (int i = 0; i < lengthQuestion; i++) {
                if (trueAnswer.charAt(i) == answer.charAt(i)) {
                    buttonsAnswer.get(i).setBackgroundResource(R.drawable.ic_tile_true);
                } else {
                    buttonsAnswer.get(i).setBackgroundResource(R.drawable.ic_tile_false);
                }
            }
            heart--;
            txtHeart.setText(String.valueOf(heart));
            if (heart > 0) {
                Message msg = new Message();
                msg.what = MSG_RESET_QUESTION;
                handler.sendMessageDelayed(msg,2000);
            } else {
                Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void resetQuestion() {
        for (int i = 0; i < lengthQuestion; i++) {
            buttonsAnswer.get(i).setText("");
            buttonsAnswer.get(i).setVisibility(View.VISIBLE);
            buttonsAnswer.get(i).setBackgroundResource(R.drawable.button_answer);
        }
        for (int i = 0; i < buttonsKeyWord.size(); i++) {
            buttonsKeyWord.get(i).setVisibility(View.VISIBLE);
        }
        flagEnabled = true;
        answer = "";
        answerNumber = 0;
    }

    private void resetButton() {
        txtMessage.setText("");
        for (int i = 0; i < buttonsAnswer.size(); i++) {
            buttonsAnswer.get(i).setText("");
            buttonsAnswer.get(i).setVisibility(View.GONE);
            buttonsAnswer.get(i).setBackgroundResource(R.drawable.button_answer);
            buttonsKeyWord.get(i).setVisibility(View.VISIBLE);
        }
    }
}
