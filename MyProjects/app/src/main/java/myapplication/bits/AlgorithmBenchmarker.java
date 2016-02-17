package myapplication.bits;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dell on 12/1/2015.
 */
public class AlgorithmBenchmarker extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText ArrayEditText;
    private Button BestCase;
    private Button AverageCase;
    private Button WorstCase;
    private Button GenerateArray;
    private Button BubbleSort;
    private Button SelectionSort;
    private Button InsertionSort;
    private Button QuickSort;
    private Button MergeSort;
    private Button benchmarkAll;
    private RadioGroup algoSelectorRadioGroup;
    private TextView BubbleSortTime;
    private TextView SelectionSortTime;
    private TextView InsertionSortTime;
    private TextView QuickSortTime;
    private TextView MergeSortTime;
    private TextView MessageDisplay;
    int[] array;
    int[] array2;
    int arraySize;
    public static final int BEST_CASE = 1;
    public static final int AVERAGE_CASE = 2;
    public static final int WORST_CASE = 3;
    String caseSelect;
    String size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algorithm_benchmarking_activity);

        ArrayEditText = (EditText) findViewById(R.id.etarray);
        BestCase = (Button) findViewById(R.id.btnBestCase);
        BestCase.setOnClickListener(this);
        AverageCase = (Button) findViewById(R.id.btnAverageCase);
        AverageCase.setOnClickListener(this);
        WorstCase = (Button) findViewById(R.id.btnWorstCase);
        WorstCase.setOnClickListener(this);
        GenerateArray = (Button) findViewById(R.id.btnGenerateArray);
        GenerateArray.setOnClickListener(this);
        BubbleSort = (Button) findViewById(R.id.btnBubbleSort);
        BubbleSort.setOnClickListener(this);
        SelectionSort = (Button) findViewById(R.id.btnSelectionSort);
        SelectionSort.setOnClickListener(this);
        InsertionSort = (Button) findViewById(R.id.btnInsertionSort);
        InsertionSort.setOnClickListener(this);
        QuickSort = (Button) findViewById(R.id.btnQuickSort);
        QuickSort.setOnClickListener(this);
        MergeSort = (Button) findViewById(R.id.btnMergeSort);
        MergeSort.setOnClickListener(this);
        benchmarkAll = (Button) findViewById(R.id.btnBenchmarkAll);
        benchmarkAll.setOnClickListener(this);
        algoSelectorRadioGroup = (RadioGroup) findViewById(R.id.algoSelector);
        algoSelectorRadioGroup.setOnCheckedChangeListener(this);
        MessageDisplay = (TextView) findViewById(R.id.tvMessageDisplay);
        BubbleSortTime = (TextView) findViewById(R.id.tvBubbleSortTime);
        SelectionSortTime = (TextView) findViewById(R.id.tvSelectionSortTime);
        InsertionSortTime = (TextView) findViewById(R.id.tvInsertionSortTime);
        QuickSortTime = (TextView) findViewById(R.id.tvQuickSortTime);
        MergeSortTime = (TextView) findViewById(R.id.tvMergeSortTime);

        //Setting onFocus to enable Radio Buttons
        ArrayEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    if (size.length() == 0) {
                    }
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        size = ArrayEditText.getText().toString();
        if (size.length() == 0) {

            MessageDisplay.setText("Array size should be greater than zero");
            return;
        }
        arraySize = Integer.parseInt(size);
        GenerateArray.setEnabled(true);

        switch (checkedId) {
            case R.id.btnBestCase:
                caseSelect = "Best case";
                break;

            case R.id.btnAverageCase:
                caseSelect = "Average case";
                break;

            case R.id.btnWorstCase:
                caseSelect = "Worst case";
                break;
        }
    }

    public void generateArray() {
        int count = 0;
        int rand;
        array = new int[Integer.parseInt(size)];
        array2 = new int[Integer.parseInt(size)];
        if (caseSelect.equals("Best case")){
            for (int i = 0; i < arraySize; i++) {
                array[i] = i + 1;
                array2[i] = array[i];
            }
        }
        if (caseSelect.equals("Average case")){
            for (int i = 0; i < arraySize; i++) {
                rand = ((int) (Math.random() * arraySize));
                array[i] = rand;
                array2[i] = array[i];
            }
        }
        if (caseSelect.equals("Worst case")){
            for (int i = arraySize - 1; i >= 0; i--) {
                array[count] = i - 1;
                array2[count] = array[count];
                count++;
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGenerateArray:
                generateArray();
                MessageDisplay.setText("Array of size " + size + " generated for " + caseSelect);
                BubbleSort.setEnabled(true);
                SelectionSort.setEnabled(true);
                InsertionSort.setEnabled(true);
                QuickSort.setEnabled(true);
                MergeSort.setEnabled(true);
                benchmarkAll.setEnabled(true);
                break;

            case R.id.btnBubbleSort:
                long bubbleTime = SortingAlgorithm.BubbleSort(array);
                BubbleSortTime.setText(String.valueOf(bubbleTime)+ "ms");
                break;

            case R.id.btnSelectionSort:
                long selectionTime = SortingAlgorithm.selectionAlgo(array);
                SelectionSortTime.setText(String.valueOf(selectionTime)+ "ms");
                break;

            case R.id.btnInsertionSort:
                long insertionTime = SortingAlgorithm.insertionSort(array);
                InsertionSortTime.setText(String.valueOf(insertionTime)+ "ms");
                break;

            case R.id.btnQuickSort:
                long quickTime = SortingAlgorithm.quickSortTime(array, 0, arraySize - 1);
                QuickSortTime.setText(String.valueOf(quickTime)+ "ms");
                break;

            case R.id.btnMergeSort:
                long mergeTime = SortingAlgorithm.mergeSort(array);
                MergeSortTime.setText(String.valueOf(mergeTime)+ "ms");
                break;

            case R.id.btnBenchmarkAll:
                bubbleTime = SortingAlgorithm.BubbleSort(array);
                BubbleSortTime.setText(String.valueOf(bubbleTime)+ "ms");
                selectionTime = SortingAlgorithm.selectionAlgo(array);
                SelectionSortTime.setText(String.valueOf(selectionTime)+ "ms");
                insertionTime = SortingAlgorithm.insertionSort(array);
                InsertionSortTime.setText(String.valueOf(insertionTime)+ "ms");
                quickTime = SortingAlgorithm.quickSortTime(array, 0, arraySize - 1);
                QuickSortTime.setText(String.valueOf(quickTime)+ "ms");
                mergeTime = SortingAlgorithm.mergeSort(array);
                MergeSortTime.setText(String.valueOf(mergeTime)+ "ms");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_algorithm_benchmarking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}