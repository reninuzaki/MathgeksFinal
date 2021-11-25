package com.example.mathgeksfinal;

public class PictureQuestion {

    private String mPictureQuestion[] = {
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
            "Can you Solve this Picture",
    };

    private String mChocie [][]  = {
            {"AMD","CMD","KMD"},
            {"Smart Phone","Android","Mobile"},
            {"App Store","Android","Software"},
            {"Chrome","Android","Software"},
            {"Dell","MI","HP"},
            {"Drop Box","Android","Software"},
            {"Box","Android","Edge"},
            {"MI","Facebook","Cross Platform"},
            {"Flutter","Framework","Cross Platform"},
            {"Git","Framework","GitHub"},
    };

    private String mImage[] = {
            "amd",
            "android",
            "appstore",
            "chrome",
            "images",
            "dropbox",
            "edge",
            "facebook",
            "flutter",
            "github",
    };

    private String mQuestionType[] = {
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
    };

    private String mCorrectAnswers [] = {
            "AMD",
            "Android",
            "App Store",
            "Chrome",
            "Dell",
            "Drop Box",
            "Edge",
            "Facebook",
            "Flutter",
            "GitHub"
    };

    public String getPictureQuestion(int q) {
        String questions = mPictureQuestion[q];
        return questions;
    }

    public String[] getChoice(int q) {
        String [] choice = mChocie[q];
        return choice;
    }

       public String getImage(int q) {
        String img = mImage[q];
        return img;
    }


    public String getType(int q){
        String type = mQuestionType[q];
        return type;

    }

    public int getLength(){
        return mPictureQuestion.length;
    }

    public String getCorrectAnswers(int q) {
        String correct = mCorrectAnswers[q];
        return correct;
    }


}
