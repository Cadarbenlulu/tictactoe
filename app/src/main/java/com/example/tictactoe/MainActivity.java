package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> winCombinations = new ArrayList<>();
    private int[] boxState = {0,0,0,0,0,0,0,0,0}; // 0 - empty box
    private int playerTurn = 1; // 1 - player X, 2 - player O
    private int totalSelectedBoxes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        winCombinations.add(new int[] {0,1,2});
        winCombinations.add(new int[] {3,4,5});
        winCombinations.add(new int[] {6,7,8});
        winCombinations.add(new int[] {0,3,6});
        winCombinations.add(new int[] {1,4,7});
        winCombinations.add(new int[] {2,5,8});
        winCombinations.add(new int[] {2,4,6});
        winCombinations.add(new int[] {0,4,8});

        binding.box11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)){
                    performAction((ImageView) view, 0);
                }
            }
        });

        binding.box12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) view, 1);
                }
            }
        });
        binding.box13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) view, 2);
                }
            }
        });
        binding.box21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) view, 3);
                }
            }
        });
        binding.box22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) view, 4);
                }
            }
        });
        binding.box23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)){
                    performAction((ImageView) view, 5);
                }
            }
        });
        binding.box31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) view, 6);
                }
            }
        });
        binding.box32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) view, 7);
                }
            }
        });
        binding.box33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)){
                    performAction((ImageView) view, 8);
                }
            }
        });

    }

    private void performAction(ImageView  imageView, int boxSelected) {
        boxState[boxSelected] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.x_mark);
            if (checkResults()) {
                Result resultDialog = new Result(MainActivity.this,
                                         binding.playerX.getText().toString() + " Wins!",
                                        MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if(totalSelectedBoxes == 9) {
                Result resultDialog = new Result(MainActivity.this,
                                         "It's a tie!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changeTurns(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.o_mark);
            if (checkResults()) {
                Result resultDialog = new Result(MainActivity.this,
                                         binding.playerO.getText().toString() + " Wins!",
                                        MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if(totalSelectedBoxes == 9) {
                Result resultDialog = new Result(MainActivity.this,
                                         "It's a tie!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changeTurns(1);
                totalSelectedBoxes++;
            }
        }

    }

    private void changeTurns(int currPlayer) {
        playerTurn = currPlayer;
        if (playerTurn == 1) {
            binding.xPlayerLayout.setBackgroundResource(R.drawable.border);
            binding.oPlayerLayout.setBackgroundResource(R.drawable.empty_box);
        } else {
            binding.oPlayerLayout.setBackgroundResource(R.drawable.border);
            binding.xPlayerLayout.setBackgroundResource(R.drawable.empty_box);
        }
    }

    private boolean checkResults(){
        boolean response = false;
        for (int i = 0; i < winCombinations.size(); i++){
            final int[] combination = winCombinations.get(i);

            if (boxState[combination[0]] == playerTurn && boxState[combination[1]] == playerTurn &&
                    boxState[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxState[boxPosition] == 0;
    }

    public void restartMatch(){
        boxState = new int[] {0,0,0,0,0,0,0,0,0}; //9 zero
        playerTurn = 1;
        totalSelectedBoxes = 1;

        binding.box11.setImageResource(R.drawable.empty_box);
        binding.box12.setImageResource(R.drawable.empty_box);
        binding.box13.setImageResource(R.drawable.empty_box);
        binding.box21.setImageResource(R.drawable.empty_box);
        binding.box22.setImageResource(R.drawable.empty_box);
        binding.box23.setImageResource(R.drawable.empty_box);
        binding.box31.setImageResource(R.drawable.empty_box);
        binding.box32.setImageResource(R.drawable.empty_box);
        binding.box33.setImageResource(R.drawable.empty_box);
    }
}