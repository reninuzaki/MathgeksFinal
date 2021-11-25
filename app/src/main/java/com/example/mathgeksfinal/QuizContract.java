package com.example.mathgeksfinal;

import android.provider.BaseColumns;

public class QuizContract {

    public QuizContract(){
    }

    public class QuestionTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTIONS = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_LEVEL_ID = "levels_id";

    }
}
