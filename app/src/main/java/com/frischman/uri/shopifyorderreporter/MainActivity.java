package com.frischman.uri.shopifyorderreporter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.frischman.uri.shopifyorderreporter.util.StringUtil;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import static com.frischman.uri.shopifyorderreporter.R.string.list_of_orders_url;
import static com.frischman.uri.shopifyorderreporter.util.CustomerUtil.getAmountCustomerSpentFromOrders;
import static com.frischman.uri.shopifyorderreporter.util.LineItemUtil.getTotalLineItemSoldFromOrders;
import static com.frischman.uri.shopifyorderreporter.util.OrderUtil.getAllOrdersFromRequest;

public class MainActivity extends AppCompatActivity {

    private final int NAPOLEAN_LOADER = 1;
    private final int BAGS_LOADER = 2;
    private Context mContext = this;
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

        initTickers();

        LoaderManager.LoaderCallbacks<Double> amountLoader = new LoaderManager.LoaderCallbacks<Double>() {
            @Override
            public Loader<Double> onCreateLoader(int id, Bundle args) {
                return new FetchAmount(mContext);
            }

            @Override
            public void onLoadFinished(Loader<Double> loader, Double data) {
                mNapoleanTicker.setText("$" + data);
            }

            @Override
            public void onLoaderReset(Loader<Double> loader) {

            }
        };

        LoaderManager.LoaderCallbacks<Integer> bagsLoader = new LoaderManager.LoaderCallbacks<Integer>() {
            @Override
            public Loader<Integer> onCreateLoader(int id, Bundle args) {
                return new FetchBagsAmount(mContext);
            }

            @Override
            public void onLoadFinished(Loader<Integer> loader, Integer data) {
                mBagsTicker.setText(String.valueOf(data));
            }

            @Override
            public void onLoaderReset(Loader<Integer> loader) {

            }
        };

        mButtonGenerateReport.setOnClickListener(v -> {
            getSupportLoaderManager().initLoader(NAPOLEAN_LOADER, null, amountLoader).forceLoad();
            getSupportLoaderManager().initLoader(BAGS_LOADER, null, bagsLoader).forceLoad();
        });

        mResetText.setOnClickListener(v -> {
            resetTickers();
        });
    }

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

    private static class FetchAmount extends AsyncTaskLoader<Double> {

        public FetchAmount(Context context) {
            super(context);
        }

        @Override
        public Double loadInBackground() {
            return getAmountCustomerSpentFromOrders(getAllOrdersFromRequest(StringUtil.getString(list_of_orders_url)), "Napoleon Batz");
        }

        @Override
        public void deliverResult(Double data) {
            super.deliverResult(data);
        }
    }

    private static class FetchBagsAmount extends AsyncTaskLoader<Integer> {

        public FetchBagsAmount(Context context) {
            super(context);
        }

        @Override
        public Integer loadInBackground() {
            return getTotalLineItemSoldFromOrders(getAllOrdersFromRequest(StringUtil.getString(list_of_orders_url)), "Awesome Bronze Bag");
        }


        @Override
        public void deliverResult(Integer data) {
            super.deliverResult(data);
        }
    }

}
