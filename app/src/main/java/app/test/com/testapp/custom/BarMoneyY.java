package app.test.com.testapp.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by YZJ on 2017/8/10.
 */

public class BarMoneyY implements IAxisValueFormatter {

    private DecimalFormat decimalFormat;

    public BarMoneyY() {
        decimalFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return decimalFormat.format(value) + " $";
    }
}
