package com.example.cliff.fitnessapp;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateWorkoutFragment extends Fragment implements View.OnClickListener {


    private ArrayList<WeightExercise> exerciseList;

    //Required empty public constructor
    public CreateWorkoutFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        exerciseList = new ArrayList<>();

        View v = inflater.inflate(R.layout.fragment_create_workout, container, false);

        Button b = (Button) v.findViewById(R.id.add_exercise_button);
        b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onStart()
    {
        Spinner spinner = (Spinner) getView().findViewById(R.id.name_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.workouts_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        super.onStart();
    }

    public void onClick(View v)
    {
        int id = v.getId();

        if (id == R.id.add_exercise_button)
        {
           //Get data from necessary fields and add it to the exercise ArrayList
            String workoutName = (String)(((Spinner)getView().findViewById(R.id.name_spinner)).getSelectedItem().toString());
            int reps = Integer.valueOf((((EditText)getView().findViewById(R.id.rep_edit_text)).getText().toString()));
            int repCount = Integer.valueOf((((EditText)getView().findViewById(R.id.rep_count_edit_text)).getText().toString()));
            int weight = Integer.valueOf((((EditText)getView().findViewById(R.id.weight_edit_text)).getText().toString()));

            WeightExercise exerciseToAdd = new WeightExercise(workoutName, reps, repCount, weight);
            exerciseList.add(exerciseToAdd);

            displayAddedWorkouts();
        }
        else if (id == R.id.create_workout_button)
        {
            //add workout to database with id's to reference the exercises that are part of it
            //also add the exercises themselves to the database.
        }
        /*if (id == R.id.add_workout_button)
        {
            SQLiteOpenHelper helper = new FitnessAppHelper(getActivity());

            try
            {
                SQLiteDatabase db = helper.getWritableDatabase();

                String workoutName = (String)(((Spinner)getView().findViewById(R.id.name_spinner)).getSelectedItem().toString());
                int reps = Integer.valueOf((((EditText)getView().findViewById(R.id.rep_edit_text)).getText().toString()));
                int repCount = Integer.valueOf((((EditText)getView().findViewById(R.id.rep_count_edit_text)).getText().toString()));
                int weight = Integer.valueOf((((EditText)getView().findViewById(R.id.weight_edit_text)).getText().toString()));

                String test = String.format("Name %s reps %d repcount %d, weight %d", workoutName, reps, repCount, weight);
                System.out.println(test);

                ContentValues exerciseValues = new ContentValues();
                exerciseValues.put("NAME", workoutName);
                exerciseValues.put("REPS", reps);
                exerciseValues.put("NUMBEROFREPS", repCount);
                exerciseValues.put("WEIGHT", weight);

                db.insert("EXERCISE", null, exerciseValues);

                String[] columns = {"WEIGHT"};
                Cursor cursor = db.query("EXERCISE", columns, null, null, null, null, null);
                cursor.moveToFirst();

                String weightValues = "";
                do
                {
                    weightValues += " " + String.valueOf(cursor.getInt(0));
                }
                while (cursor.moveToNext());

                TextView databaseContents = getView().findViewById(R.id.exercise_database_contents);
                databaseContents.setText(weightValues);
            }
            catch (SQLiteException e)
            {
                Toast toast = Toast.makeText(getActivity(), "Database Unavailabe", Toast.LENGTH_SHORT);
                toast.show();
            }
        }*/
    }

    private void displayAddedWorkouts()
    {
        String exerciseNames = "";

        for (int i = 0; i < exerciseList.size(); i ++)
        {
            exerciseNames += " " + exerciseList.get(i).getName();
        }

        TextView addedExercises = getView().findViewById(R.id.exercise_database_contents);
        addedExercises.setText(exerciseNames);
    }
}