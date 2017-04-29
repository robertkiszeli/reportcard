package com.robertkiszelirk.reportcard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportCardActivity extends AppCompatActivity {

    ReportCard reportCard;

    boolean nextClass = true;

    TextView readingGrade;
    TextView languageArtsGrade;
    TextView basicMathGrade;
    TextView algebraGrade;
    TextView artGrade;
    TextView healthGrade;

    TextView avaragePercentageText;
    TextView avarageGradeText;

    TextView comment;

    boolean validMark;

    int selectedSpinnerItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_card);

        Intent intent = getIntent();

        TextView studentName = (TextView) findViewById(R.id.report_card_student_name);

        studentName.setText(intent.getStringExtra(getString(R.string.student_first_name)) +
                " " +
                intent.getStringExtra(getString(R.string.student_second_name)));

        reportCard = new ReportCard(intent.getDoubleExtra(getString(R.string.reading_mark),0.0),
                intent.getDoubleExtra(getString(R.string.language_art_mark),0.0),
                intent.getDoubleExtra(getString(R.string.basic_math_mark),0.0),
                intent.getDoubleExtra(getString(R.string.algebra_mark),0.0),
                intent.getDoubleExtra(getString(R.string.art_mark),0.0),
                intent.getDoubleExtra(getString(R.string.health_mark),0.0),
                getApplicationContext());

        readingGrade = (TextView) findViewById(R.id.report_card_reading_grade);
        languageArtsGrade = (TextView) findViewById(R.id.report_card_language_arts_grade);
        basicMathGrade = (TextView) findViewById(R.id.report_card_basic_math_grade);
        algebraGrade = (TextView) findViewById(R.id.report_card_algebra_grade);
        artGrade = (TextView) findViewById(R.id.report_card_art_grade);
        healthGrade = (TextView) findViewById(R.id.report_card_health_grade);

        avaragePercentageText = (TextView) findViewById(R.id.report_card_avarage_percentage);

        avarageGradeText = (TextView) findViewById(R.id.report_card_avarage_grade);

        comment  = (TextView) findViewById(R.id.report_card_comment);

        setData();

        final Spinner changePercentageSpinner = (Spinner) findViewById(R.id.report_card_change_percentage_spinner);
        ArrayAdapter changePercentageAdapter = ArrayAdapter.createFromResource(this, R.array.subjects, R.layout.support_simple_spinner_dropdown_item);
        changePercentageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        changePercentageSpinner.setAdapter(changePercentageAdapter);

        changePercentageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText changePercentageEditText = (EditText) findViewById(R.id.report_card_change_percentage_edit_text);

        Button changePercentageButton = (Button) findViewById(R.id.report_card_change_percentage_button);
        changePercentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validMark = true;

                double changedMark = checkMark(changePercentageEditText);

                if(validMark) {

                    switch (selectedSpinnerItem) {
                        case 0:
                            reportCard.setReadingMark(changedMark);
                            setData();
                            break;
                        case 1:
                            reportCard.setLanguageArtsMark(changedMark);
                            setData();
                            break;
                        case 2:
                            reportCard.setBasicMathMark(changedMark);
                            setData();
                            break;
                        case 3:
                            reportCard.setAlgebraMark(changedMark);
                            setData();
                            break;
                        case 4:
                            reportCard.setArtMark(changedMark);
                            setData();
                            break;
                        case 5:
                            reportCard.setHealthMark(changedMark);
                            setData();
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private double checkMark(EditText editText) {

        double mark;

        if(!editText.getText().toString().equals("")) {

            mark = Double.parseDouble(editText.getText().toString());

            if (mark > 100) {
                editText.setBackgroundColor(Color.RED);
                validMark = false;
                Toast.makeText(getApplicationContext(), R.string.report_card_check_mark_value_text, Toast.LENGTH_LONG).show();
            } else {
                editText.setBackgroundColor(Color.WHITE);
            }

            return mark;
        }

        return 0;
    }

    private void setData() {

        nextClass = true;

        setGrade(readingGrade,reportCard.getReadingMark());
        setGrade(languageArtsGrade,reportCard.getLanguageArtsMark());
        setGrade(basicMathGrade,reportCard.getBasicMathMark());
        setGrade(algebraGrade,reportCard.getAlgebraMark());
        setGrade(artGrade,reportCard.getArtMark());
        setGrade(healthGrade,reportCard.getHealthMark());

        avaragePercentageText.setText(String.format(getString(R.string.report_card_percentage_value_text),countAvaragePercentage()));

        setGrade(avarageGradeText,countAvaragePercentage());

        comment.setText(reportCard.toString(nextClass));
    }

    private void setGrade(TextView gradeTextView,double mark) {

        if(mark >= reportCard.getbHigh()){gradeTextView.setText(R.string.grade_a);return;}
        if(mark >= reportCard.getcHigh()){gradeTextView.setText(R.string.grade_b);return;}
        if(mark >= reportCard.getdHigh()){gradeTextView.setText(R.string.grade_c);return;}
        if(mark >= reportCard.getfHigh()){gradeTextView.setText(R.string.grade_d);return;}

        gradeTextView.setText(R.string.grade_f);
        nextClass = false;

    }

    private double countAvaragePercentage() {

        return ((reportCard.getReadingMark() +
                reportCard.getLanguageArtsMark() +
                reportCard.getBasicMathMark() +
                reportCard.getAlgebraMark() +
                reportCard.getArtMark() +
                reportCard.getHealthMark()) /
                reportCard.getTOTAL_SUBJECTS());
    }
}
