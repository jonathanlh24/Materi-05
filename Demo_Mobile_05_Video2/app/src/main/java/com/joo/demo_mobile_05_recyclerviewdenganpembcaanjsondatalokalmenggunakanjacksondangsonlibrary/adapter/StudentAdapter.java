package com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.R;
import com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.databinding.StudentItemBinding;
import com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.entity.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{

    private ArrayList<Student> students;
    private StudentDataListener listener;

    public StudentAdapter(ArrayList<Student> students, StudentDataListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
    holder.setStudentData(students.get(position));
    holder.itemView.setOnClickListener(view -> listener.studentDataClicked(students.get(position)));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{

        private StudentItemBinding binding;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = StudentItemBinding.bind(itemView);
        }

        public void setStudentData(Student student){
            binding.tvId.setText(student.getId());
            binding.tvName.setText(student.getFirstName() + " " + student.getLastName());
        }
    }

    public interface StudentDataListener{
        void studentDataClicked(Student student);
    }
}
