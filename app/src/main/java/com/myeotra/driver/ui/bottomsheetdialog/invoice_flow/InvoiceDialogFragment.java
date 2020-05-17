package com.myeotra.driver.ui.bottomsheetdialog.invoice_flow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myeotra.driver.MvpApplication;
import com.myeotra.driver.R;
import com.myeotra.driver.base.BaseFragment;
import com.myeotra.driver.common.Constants;
import com.myeotra.driver.data.network.model.Request_;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myeotra.driver.MvpApplication.DATUM;

public class InvoiceDialogFragment extends BaseFragment implements InvoiceDialogIView {

    @BindView(R.id.promotion_amount)
    TextView promotionAmount;
    @BindView(R.id.wallet_amount)
    TextView walletAmount;
    @BindView(R.id.booking_id)
    TextView bookingId;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.payable_amount)
    TextView payableAmount;
    @BindView(R.id.payment_mode_img)
    ImageView paymentModeImg;
    @BindView(R.id.payment_mode_layout)
    LinearLayout paymentModeLayout;
    @BindView(R.id.llAmountToBePaid)
    LinearLayout llAmountToBePaid;
    Unbinder unbinder;
    @BindView(R.id.btnConfirmPayment)
    Button btnConfirmPayment;
    @BindView(R.id.tvTollCharges)
    TextView tvTollCharges;

    InvoiceDialogPresenter presenter;
    @BindView(R.id.lblPaymentType)
    TextView lblPaymentType;
    String TAG = "AAAA";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invoice_dialog;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        presenter = new InvoiceDialogPresenter();
        presenter.attachView(this);
        // setCancelable(false);

        Log.e("data", "billing response " + new Gson().toJson(DATUM));
        if (DATUM != null) {

            Log.e(TAG, "initView: data noy null");
            Request_ datum = DATUM;
            bookingId.setText(datum.getBookingId());
            if (datum.getPayment() != null) {

                Log.e(TAG, "getPayment done");
                if (datum.getPayment().getTotal() > 0)
                    totalAmount.setText(Constants.Currency + " " +
                            MvpApplication.getInstance().getNewNumberFormat(Double.parseDouble(datum.getPayment().getTotal() + "")));
                if (datum.getPayment().getPayable() > 0) {
                    llAmountToBePaid.setVisibility(View.VISIBLE);
                    payableAmount.setText(Constants.Currency + " " +
                            MvpApplication.getInstance().getNewNumberFormat(Double.parseDouble(datum.getPayment().getPayable() + "")));
                } else llAmountToBePaid.setVisibility(View.GONE);

            } else {
                Log.e(TAG, "getPayment not");
            }
        } else {
            Log.e(TAG, "initView: data null");

        }

        return view;
    }

    @Override
    public void onSuccess(Object object) {
        hideLoading();
        activity().sendBroadcast(new Intent("INTENT_FILTER"));
    }

    @Override
    public void onError(Throwable e) {
        hideLoading();
        try {
            if (e != null)
                onErrorBase(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @OnClick(R.id.btnConfirmPayment)
    public void onViewClicked() {

        if (DATUM != null) {
            Request_ datum = DATUM;
            HashMap<String, Object> map = new HashMap<>();
            map.put("status", Constants.checkStatus.COMPLETED);
            map.put("_method", Constants.checkStatus.PATCH);
            showLoading();
            presenter.statusUpdate(map, datum.getId());
        }
    }

   /* @SuppressLint("SetTextI18n")
    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        presenter = new InvoiceDialogPresenter();
        presenter.attachView(this);
        setCancelable(false);
        numberFormat = MvpApplication.getInstance().getNewNumberFormat();

        if (DATUM != null) {
            Request_ datum = DATUM;
            bookingId.setText(datum.getBookingId());
            if (datum.getPayment() != null)
                if (datum.getPayment().getTotal() != 0 ||
                        datum.getPayment().getPayable() != 0) {
                    totalAmount.setText(Constants.Currency + " " + numberFormat.format(Double.parseDouble(datum.getPayment().getTotal() + "")));
                    payableAmount.setText(Constants.Currency + " " + numberFormat.format(Double.parseDouble(datum.getPayment().getPayable() + "")));
                }
        }
    }

    *//*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        getActivity().getSupportFragmentManager().putFragment(outState, "InvoiceDialogFragment", InvoiceDialogFragment.this);
    }*//*

    @OnClick(R.ID.btnConfirmPayment)
    public void onViewClicked() {

        if (DATUM != null) {
            Request_ datum = DATUM;
            HashMap<String, Object> map = new HashMap<>();
            map.put("status", Constants.checkStatus.COMPLETED);
            map.put("_method", Constants.checkStatus.PATCH);
            showLoading();
            presenter.statusUpdate(map, datum.getId());
        }
    }

    @Override
    public void onSuccess(Object object) {
        dismissAllowingStateLoss();
        hideLoading();
        activity().sendBroadcast(new Intent("INTENT_FILTER"));
    }


    @Override
    public void onError(Throwable e) {
        hideLoading();
    }*/
}
