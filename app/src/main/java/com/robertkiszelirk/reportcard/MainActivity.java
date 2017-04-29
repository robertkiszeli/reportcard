package com.robertkiszelirk.reportcard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean validMark = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText studentFirstName = (EditText) findViewById(R.id.student_first_name_edit_text);
        final EditText studentSecondName = (EditText) findViewById(R.id.student_second_name_edit_text);

        final EditText readingEditText = (EditText) findViewById(R.id.reading_edit_text);
        final EditText languageArtsEditText = (EditText) findViewById(R.id.language_art_edit_text);
        final EditText basicMathEditText = (EditText) findViewById(R.id.basic_math_edit_text);
        final EditText algebraEditText = (EditText) findViewById(R.id.algebra_edit_text);
        final EditText artEditText = (EditText) findViewById(R.id.art_edit_text);
        final EditText healthEditText = (EditText) findViewById(R.id.health_edit_text);

        Button checkResultButton = (Button) findViewById(R.id.check_result_button);
        checkResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validMark = true;

                Intent intent = new Intent(MainActivity.this, ReportCardActivity.class);
                intent.putExtra(getString(R.string.student_first_name), studentFirstName.getText().toString());
                intent.putExtra(getString(R.string.student_second_name), studentSecondName.getText().toString());
                intent.putExtra(getString(R.string.reading_mark), checkEditText(readingEditText));
                intent.putExtra(getString(R.string.language_art_mark), checkEditText(languageArtsEditText));
                intent.putExtra(getString(R.string.basic_math_mark), checkEditText(basicMathEditText));
                intent.putExtra(getString(R.string.algebra_mark), checkEditText(algebraEditText));
                intent.putExtra(getString(R.string.art_mark), checkEditText(artEditText));
                intent.putExtra(getString(R.string.health_mark), checkEditText(healthEditText));

                if (validMark) {
                    startActivity(intent);
                }
            }
        });
    }

    private double checkEditText(EditText editText) {

        double mark;

        if (!editText.getText().toString().equals("")) {

            mark = Double.parseDouble(editText.getText().toString());

            if (mark > 100) {
                editText.setBackgroundColor(Color.RED);
                validMark = false;
                Toast.makeText(getApplicationContext(), R.string.valu_check_in_student_data, Toast.LENGTH_LONG).show();
            } else {
                editText.setBackgroundColor(Color.WHITE);
            }

            return mark;
        }
        return 0;
    }
}
