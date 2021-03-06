package com.theo.videogameqizz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuestionsList extends RecyclerView.Adapter<QuestionsList.ViewHolder> implements View.OnClickListener {

    private final List<Question> questions;
    int i;

    public QuestionsList(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionsList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsList.ViewHolder holder, int position) {
        Question question = questions.get(position);

        switch (question.getMedia().getType()) {
            case "image":
                Picasso.with(holder.questionImage.getContext()).load(question.getMedia().getLink()).into(holder.questionImage);
                break;
            case "video":
//                videoView.getYouTubePlayerWhenReady(youTubePlayer -> {
//                    youTubePlayer.cueVideo(question.getMedia().getLink(), 0);
//                });
                break;
            default:
                break;
        }
        holder.questions.setText(question.question);
        holder.answer1.setText(question.answer.getGoodAnswer());
        holder.answer2.setText(question.answer.getOtherAnswerOne());
        holder.answer3.setText(question.answer.getOtherAnswerTwo());

        holder.itemView.setTag(question);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.questionCardLayout:
                Question question = (Question) view.getTag();
                List<Question> quest = new ArrayList<>();
                quest.add(question);
                Context context = view.getContext();
                Intent questions = new Intent(context,FlashCardActivity.class);
                questions.putExtra("questions", new Questions("", quest));
                context.startActivity(questions);
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView questionImage;
        final TextView questions;
        final TextView answer1;
        final TextView answer2;
        final TextView answer3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionImage = itemView.findViewById(R.id.questionListImageView);
            questions = itemView.findViewById(R.id.questionListTextView);
            answer1 = itemView.findViewById(R.id.answer1ListTextView);
            answer2 = itemView.findViewById(R.id.answer2ListTextView);
            answer3 = itemView.findViewById(R.id.answer3ListTextView);
        }
    }
}
