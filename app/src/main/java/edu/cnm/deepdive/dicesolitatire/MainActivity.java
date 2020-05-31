package edu.cnm.deepdive.dicesolitatire;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.dicesolitatire.model.Roll;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private static final String PAIR_LABEL_ID_FORMAT = "pair_%d_label";
  private static final String PAIR_COUNT_ID_FORMAT = "pair_%d_count";
  private static final String SCRATCH_LABEL_ID_FORMAT = "scratch_%d_label";
  private static final String SCRATCH_COUNT_ID_FORMAT = "scratch_%d_count";

  private int minPairValue = 2;
  private int maxPairValue;
  private int minScratchValue = 2;
  private TextView[] pairLabels;
  private ProgressBar[] pairCounts;
  private TextView[] scratchLabels;
  private ProgressBar[] scratchCounts;
  private Button roller;
  private TextView rollDisplay;
  private Random rng;

  @Override

  //Code for pairs
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    maxPairValue = 2 * Roll.NUM_FACES;
    pairLabels = new TextView[maxPairValue - minPairValue + 1];
    pairCounts = new ProgressBar[maxPairValue - minPairValue + 1];
    Resources res = getResources();
    rng = new Random();
    NumberFormat formatter = NumberFormat.getInstance();
    for (int i = minPairValue; i <= maxPairValue; i++ ){
      String labelIdString = String.format(PAIR_LABEL_ID_FORMAT, i);
      int labelId = res.getIdentifier(labelIdString, "id", getOpPackageName());
      pairLabels[i - minPairValue] = findViewById(labelId);
      pairLabels[i - minPairValue].setText(formatter.format(i));
      String countIdString = String.format(PAIR_COUNT_ID_FORMAT, i);
      int countId = res.getIdentifier(countIdString, "id", getPackageName());
      pairCounts[i - minPairValue] = findViewById(countId);
      pairCounts[i - minPairValue].setProgress(1 + rng.nextInt(10));
    }
    roller = findViewById(R.id.roller);
    rollDisplay = findViewById(R.id.roll_display);
    roller.setOnClickListener(new RollerListener());

    //Code for scratch
    scratchLabels = new TextView[Roll.NUM_FACES];
    scratchCounts = new ProgressBar[Roll.NUM_FACES];
    for (int i = 0; i <= Roll.NUM_FACES; i++) {
      String labelIdString = String.format(SCRATCH_LABEL_ID_FORMAT, i);
      int labelId = res.getIdentifier(labelIdString, "id", getOpPackageName());
      scratchLabels[i - minScratchValue] = findViewById(labelId);
      scratchLabels[i - minScratchValue].setText(formatter.format(i));
      String countIdString = String.format(SCRATCH_COUNT_ID_FORMAT, i);
      int countId = res.getIdentifier(countIdString, "id", getPackageName());
      scratchCounts[1 - minScratchValue] = findViewById(countId);
      scratchCounts[1 - minScratchValue].setProgress(1 + rng.nextInt(7));
    }
  }

  private class RollerListener implements OnClickListener {

    @Override
    public void onClick(View v) {
      Roll roll = new Roll(rng);
      rollDisplay.setText(Arrays.toString(roll.getDice()));
    }
  }

}
