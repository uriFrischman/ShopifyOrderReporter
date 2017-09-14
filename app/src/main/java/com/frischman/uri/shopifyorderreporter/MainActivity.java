package com.frischman.uri.shopifyorderreporter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TickerView mNapoleanTicker;
    private TickerView mBagsTicker;
    private Button mButtonGenerateReport;
    private TextView mResetText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonGenerateReport = (Button) findViewById(R.id.generateReportButton);
        mResetText = (TextView) findViewById(R.id.resetText);
    private void resetTickers() {
        mBagsTicker.setText("0", true);
        mNapoleanTicker.setText("$0000.00", true);
    }
    private void initTickers() {
        mNapoleanTicker = (TickerView) findViewById(R.id.napoleanTicker);
        mNapoleanTicker.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
        mNapoleanTicker.setAnimationDuration(3000);

        mBagsTicker = (TickerView) findViewById(R.id.bagsAmountTicker);
        mBagsTicker.setCharacterList(TickerUtils.getDefaultNumberList());
        mBagsTicker.setAnimationDuration(3000);
        mBagsTicker.setAnimationInterpolator(new OvershootInterpolator());

        resetTickers();
    }
}
