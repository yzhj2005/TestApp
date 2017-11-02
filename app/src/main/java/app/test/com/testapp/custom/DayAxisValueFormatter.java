package app.test.com.testapp.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by YZJ on 2017/8/10.
 */

public class DayAxisValueFormatter implements IAxisValueFormatter {

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return null;
    }
}
