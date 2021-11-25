package com.example.mathgeksfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mathgeksfinal.QuizContract.*;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GoQuiz.db";
    private static final int DATBASE_VERSION = 6;

    private SQLiteDatabase db;


    QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTIONS + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionTable.COLUMN_LEVEL_ID + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable() {
        //Beginner Questions

        //Level 1
        Questions q1 = new Questions("What is 6 X 3 ?", "18", "20", "24", "32", 1, Questions.CATEGORY_BEGINNER, Questions.LEVEL1);
        addQuestions(q1);

        Questions q2 = new Questions("What is 4 x 3 ?", "14", "12", "24", "35", 2, Questions.CATEGORY_BEGINNER, Questions.LEVEL1);
        addQuestions(q2);

        Questions q2a1 = new Questions("What is 10 x 2 ?", "20", "10", "30", "9", 1, Questions.CATEGORY_BEGINNER, Questions.LEVEL1);
        addQuestions(q2a1);

        Questions q2a2 = new Questions("What is 28 ÷ 4 ?", "8", "4", "7", "9", 3, Questions.CATEGORY_BEGINNER, Questions.LEVEL1);
        addQuestions(q2a2);

        Questions q2a3 = new Questions("What is 75 ÷ 15 ?", "20", "10", "7", "5", 4, Questions.CATEGORY_BEGINNER, Questions.LEVEL1);
        addQuestions(q2a3);

        Questions q2a4 = new Questions("What is 72 ÷ 4 ?", "10", "19", "7", "18", 4, Questions.CATEGORY_BEGINNER, Questions.LEVEL1);
        addQuestions(q2a4);

        //Level 2
        Questions q3 = new Questions("721 ÷ 7", "105", "95", "68", "103", 4, Questions.CATEGORY_BEGINNER, Questions.LEVEL2);
        addQuestions(q3);

        Questions q4 = new Questions("9 X 3 ?", "30", "29", "53", "27", 4, Questions.CATEGORY_BEGINNER, Questions.LEVEL2);
        addQuestions(q4);

        Questions q4a2 = new Questions("852 ÷ 6 ?", "242", "139", "252", "142", 4, Questions.CATEGORY_BEGINNER, Questions.LEVEL2);
        addQuestions(q4a2);

        Questions q4a4 = new Questions("12 X 3 ?", "39", "36", "28", "14", 2, Questions.CATEGORY_BEGINNER, Questions.LEVEL2);
        addQuestions(q4a4);

        Questions q4a5 = new Questions("8 X 2 ?", "16", "25", "32", "15", 1, Questions.CATEGORY_BEGINNER, Questions.LEVEL2);
        addQuestions(q4a5);

        //Level3
        Questions q5 = new Questions("258 ÷ 80 ", "3R18", "3R12", "4R20", "2R20", 1, Questions.CATEGORY_BEGINNER, Questions.LEVEL3);
        addQuestions(q5);

        Questions qa5 = new Questions("196 ÷ 50 ", "5R2", "3R46", "2R25", "4RR46", 2, Questions.CATEGORY_BEGINNER, Questions.LEVEL3);
        addQuestions(qa5);

        Questions qb5 = new Questions("474 ÷ 40 ", "12R35", "15R32", "9R25", "11R34", 4, Questions.CATEGORY_BEGINNER, Questions.LEVEL3);
        addQuestions(qb5);

        Questions qc5 = new Questions("834 ÷ 6  ", "132", "139", "148", "242", 2, Questions.CATEGORY_BEGINNER, Questions.LEVEL3);
        addQuestions(qc5);

        Questions qd5 = new Questions("90 ÷ 5 ", "18", "25", "30", "15", 1, Questions.CATEGORY_BEGINNER, Questions.LEVEL3);
        addQuestions(qc5);

        //Advance Questions
        //Level 1
        Questions q6 = new Questions("A Triangle is closed planar shape with ?", "2 sides", "4 sides", "3 sides", "5sides", 3, Questions.CATEGORY_ADVANCE, Questions.LEVEL1);
        addQuestions(q6);

        Questions q7 = new Questions("A closed planar shape with 5 sides is called a ?", "pentagon", "hexagon", "square", "heptagon", 1, Questions.CATEGORY_ADVANCE, Questions.LEVEL1);
        addQuestions(q7);

        Questions q8 = new Questions("A closed planar shape with 4 slides is called a?", "segment", "hexagon", "heptagon", "quadrilateral", 3, Questions.CATEGORY_ADVANCE, Questions.LEVEL1);
        addQuestions(q8);

        Questions qc8 = new Questions("A line segment is defined by?", "1 point", "3 points", "2 points", "4 points", 2, Questions.CATEGORY_ADVANCE, Questions.LEVEL1);
        addQuestions(qc8);

        Questions qd8 = new Questions("Which of the statements are best to describe a square", "A square has 4 side and 4 right angles", "A square has 4 equal sides", "A square has 4 right angles", "2 pairs of parallel sides", 1, Questions.CATEGORY_ADVANCE, Questions.LEVEL1);
        addQuestions(qd8);

        Questions qe8 = new Questions("A triangle is closed planar shape with", "2 sides", "3 sides", "7 sides", "4 sides",1, Questions.CATEGORY_ADVANCE, Questions.LEVEL1);
        addQuestions(qe8);

        //Level 2
        Questions qf8 = new Questions("L2 3 1/2 + 5 1/3 is?", "8", "8 2/5s", "7 sides", "4 sides", 2, Questions.CATEGORY_ADVANCE, Questions.LEVEL2);
        addQuestions(qf8);

        Questions qg8 = new Questions("Which two fractions are equivalent", "5/2 and 2/5", "4/3 and 8/6", "1/4 and 2/4", "2/3 and 1/3", 2, Questions.CATEGORY_ADVANCE, Questions.LEVEL2);
        addQuestions(qg8);

        Questions qh8 = new Questions(" L1 5 2/3 - 3 1/2 ", "2", "1 2/5", "2 7/6", "2 1/6", 4, Questions.CATEGORY_ADVANCE, Questions.LEVEL2);
        addQuestions(qh8);

        Questions qi8 = new Questions(" L1 5/2 ÷ 3/4 ", "10/3", "10/8", "13/4", "1", 1, Questions.CATEGORY_ADVANCE, Questions.LEVEL2);
        addQuestions(qi8);

        Questions qk8 = new Questions(" L1 5 ÷ 1/7 ", "5/7", "6/7", "1/35", "35", 4, Questions.CATEGORY_ADVANCE, Questions.LEVEL2);
        addQuestions(qk8);

        //Level 3

        Questions ql8 = new Questions(" L1 2/5 X 3/7 ", "14/15", "6/35", "35/6", "15/14", 2, Questions.CATEGORY_ADVANCE, Questions.LEVEL3);
        addQuestions(ql8);

        Questions qm8 = new Questions("To have a + 1 3/4 = 2, a must be equal to ", "1", "3/4", "1/2",   "1/4", 4, Questions.CATEGORY_ADVANCE, Questions.LEVEL3);
        addQuestions(qm8);

        Questions qn8 = new Questions("Write 2 1/3", "2/3", "7/3", "1/3", "6", 2, Questions.CATEGORY_ADVANCE, Questions.LEVEL3);
        addQuestions(qn8);

        Questions qo8 = new Questions("Write 31/8 as mixed number", "4", "4 7/8", "3 1/8", "3 7/8", 4, Questions.CATEGORY_ADVANCE, Questions.LEVEL3);
        addQuestions(qo8);

        Questions qp8 = new Questions("3 1/4 = ", "3 x 1/4", "3/4", "3 + 1/4", "4/3", 2, Questions.CATEGORY_ADVANCE, Questions.LEVEL3);
        addQuestions(qp8);


        //Intermediate Questions
        //Level1
        Questions q9 = new Questions("5 ÷ 1/7?", "10/3", "10/8", "13/4", "1", 2, Questions.CATEGORY_INTERMEDIATE, Questions.LEVEL1);
        addQuestions(q9);

        Questions qa9 = new Questions("5 ÷ 1/7?", "10/3", "10/8", "13/4", "1", 2, Questions.CATEGORY_INTERMEDIATE, Questions.LEVEL1);
        addQuestions(qa9);

        //Level2
        Questions q10 = new Questions("A line segment is defined by?", "8 2/5", "8 5/6", "2/5", "8", 2, Questions.CATEGORY_INTERMEDIATE, Questions.LEVEL1);
        addQuestions(q9);

        //Level 3
        Questions q11 = new Questions("A line segment is defined by?", "8 2/5", "8 5/6", "2/5", "8", 2, Questions.CATEGORY_INTERMEDIATE, Questions.LEVEL1);
        addQuestions(q9);

    }

    private void addQuestions(Questions question) {

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTIONS, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY, question.getCategory());
        cv.put(QuestionTable.COLUMN_LEVEL_ID, question.getLevels());
        db.insert(QuestionTable.TABLE_NAME, null, cv);

    }

    public ArrayList<Questions> getAllQuestionsWithCategoryAndLevels(int levelsID, String category) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();


        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTIONS,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY,
                QuestionTable.COLUMN_LEVEL_ID
        };

        String selection = QuestionTable.COLUMN_LEVEL_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_CATEGORY + " = ? ";


        String selectionArgs[] = {String.valueOf(levelsID), category};


        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_QUESTIONS)));
                question.setOption1(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndexOrThrow(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_CATEGORY)));
                question.setLevels(c.getInt(c.getColumnIndexOrThrow(QuestionTable.COLUMN_LEVEL_ID)));
                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();


        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTIONS,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };


        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_QUESTIONS)));
                question.setOption1(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndexOrThrow(QuestionTable.COLUMN_ANSWER_NR)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

    public ArrayList<Questions> getAllQuestionsWithCategory(String category) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();


        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTIONS,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? ";
        String selectionArgs[] = {category};


        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_QUESTIONS)));
                question.setOption1(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndexOrThrow(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndexOrThrow(QuestionTable.COLUMN_CATEGORY)));
                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

}
