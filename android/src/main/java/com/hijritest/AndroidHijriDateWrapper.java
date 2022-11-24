package com.hijritest;

import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import com.hijritest.rnHijriDatePicker.RNConstants;
import com.hijritest.rnHijriDatePicker.RNDate;
//import com.hijritest.rnHijriDatePicker.date.hijri.HijriDatePickerDialog;
// import com.hijritest.rnHijriDatePicker.date.hijri.HijriDatePickerDialog;
import net.alhazmy13.hijridatepicker.date.hijri.HijriDatePickerDialog;

import java.util.Calendar;

import javax.annotation.Nonnull;

@ReactModule(name = AndroidHijriDateWrapper.FRAGMENT_TAG)
public class AndroidHijriDateWrapper extends ReactContextBaseJavaModule {

    @VisibleForTesting
    public static final String FRAGMENT_TAG = "RNHijriDatePickerAndroid";

    public AndroidHijriDateWrapper(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override
    public @Nonnull String getName() {
        return AndroidHijriDateWrapper.FRAGMENT_TAG;
    }

    @ReactMethod
    public void open(@Nullable final ReadableMap options, Promise promise) {
        FragmentActivity activity = (FragmentActivity) getCurrentActivity();
        if (activity == null) {
            promise.reject(
                    RNConstants.ERROR_NO_ACTIVITY,
                    "Tried to open a DatePicker dialog while not attached to an Activity");
            return;
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        final HijriDatePickerDialog oldFragment = (HijriDatePickerDialog) fragmentManager
                .findFragmentByTag(FRAGMENT_TAG);

        if (oldFragment != null && options != null) {
            Log.d("oldFragment", "oldFragment ***************");
            return;
        }
        Log.d("newFragment", "newFragment ***************");

        RNDate rnDate = new RNDate(createFragmentArguments(options));

      final DatePickerDialogListener listener = new DatePickerDialogListener(promise);
      UmmalquraCalendar now = new UmmalquraCalendar();

      Log.d("hijridayofmonth", ""+now.get(Calendar.DAY_OF_MONTH));
      Log.d("hijridayofmonth", ""+now.get(Calendar.YEAR));
      Calendar cal = Calendar.getInstance();
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH);
      int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

      Log.d("hijridayofmonth", "year "+year);
      Log.d("hijridayofmonth", "month "+month);
      Log.d("hijridayofmonth", "dayOfMonth "+dayOfMonth);

      HijriDatePickerDialog hijriDatePickerDialog = HijriDatePickerDialog.newInstance(listener,  now.get(Calendar.YEAR), now.get(month),now.get(dayOfMonth));

        hijriDatePickerDialog.setOnDismissListener(listener);

        hijriDatePickerDialog.show(fragmentManager, FRAGMENT_TAG);
    }

    private Bundle createFragmentArguments(ReadableMap options) {
        final Bundle args = new Bundle();
        if (options.hasKey(RNConstants.ARG_VALUE) && !options.isNull(RNConstants.ARG_VALUE)) {
            args.putLong(RNConstants.ARG_VALUE, (long) options.getDouble(RNConstants.ARG_VALUE));
        }
        if (options.hasKey(RNConstants.ARG_MINDATE) && !options.isNull(RNConstants.ARG_MINDATE)) {
            args.putLong(RNConstants.ARG_MINDATE, (long) options.getDouble(RNConstants.ARG_MINDATE));
        }
        if (options.hasKey(RNConstants.ARG_MAXDATE) && !options.isNull(RNConstants.ARG_MAXDATE)) {
            args.putLong(RNConstants.ARG_MAXDATE, (long) options.getDouble(RNConstants.ARG_MAXDATE));
        }
        if (options.hasKey(RNConstants.LANGUAGE) && !options.isNull(RNConstants.LANGUAGE)) {
            args.putString(RNConstants.LANGUAGE, options.getString(RNConstants.LANGUAGE));
        }

        return args;
    }

    private class DatePickerDialogListener
            implements HijriDatePickerDialog.OnDateSetListener, DialogInterface.OnDismissListener {

        private final Promise mPromise;
        private boolean mPromiseResolved = false;

        public DatePickerDialogListener(final Promise promise) {
            mPromise = promise;
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            if (!mPromiseResolved && getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableMap result = new WritableNativeMap();
                result.putString("action", RNConstants.ACTION_DISMISSED);
                mPromise.resolve(result);
                mPromiseResolved = true;
            }
        }

        @Override
        public void onDateSet(HijriDatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

            if (!mPromiseResolved && getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableMap result = new WritableNativeMap();
                result.putString("action", RNConstants.ACTION_DATE_SET);
                result.putInt("year", year);
                result.putInt("month", monthOfYear);
                result.putInt("day", dayOfMonth);
                mPromise.resolve(result);
                mPromiseResolved = true;

            }

        }
    }
}
