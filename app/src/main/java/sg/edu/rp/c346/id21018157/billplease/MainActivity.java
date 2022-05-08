package sg.edu.rp.c346.id21018157.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvAmtDisplay;
    EditText etInputAmt;
    TextView tvPaxDisplay;
    EditText etInputPax;
    CheckBox cbSVC;
    CheckBox cbGST;
    TextView tvDiscountDisplay;
    EditText etInputDiscount;
    TextView tvPaymentDisplay;
    RadioGroup rgPayment;
    RadioButton rgPaymentCash;
    RadioButton rgPaymentPaynow;
    Button btnSplitBillDisplay;
    Button btnResetDisplay;
    TextView tvDisplayMessage;
    TextView tvDisplayMessage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking field variables to UI widgets
        tvAmtDisplay = findViewById(R.id.textViewAmountDisplay);
        etInputAmt = findViewById(R.id.editNumberInputAmt);
        tvPaxDisplay = findViewById(R.id.textViewPaxDisplay);
        etInputPax = findViewById(R.id.editNumberInputPax);
        cbSVC = findViewById(R.id.checkBoxSVC);
        cbGST = findViewById(R.id.checkBoxGST);
        tvDiscountDisplay = findViewById(R.id.textViewDiscountDisplay);
        etInputDiscount = findViewById(R.id.editNumberInputDiscount);
        tvPaymentDisplay = findViewById(R.id.textViewPaymentDisplay);
        rgPayment = findViewById(R.id.radioGroupPayment);
        rgPaymentCash = findViewById(R.id.radioButtonCash);
        rgPaymentPaynow = findViewById(R.id.radioButtonPaynow);
        btnSplitBillDisplay = findViewById(R.id.buttonSplitBillDisplay);
        btnResetDisplay = findViewById(R.id.buttonResetDisplay);
        tvDisplayMessage = findViewById(R.id.textViewDisplayMessage);
        tvDisplayMessage2= findViewById(R.id.textViewDisplayMessage2);


        btnSplitBillDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // converting str to double (str: string.valueOf, double: DpD)
                double totalAmt = Double.parseDouble(String.valueOf(etInputAmt.getText()));
                double totalPax = Double.parseDouble(String.valueOf(etInputPax.getText()));
                double discount = Double.parseDouble(String.valueOf(etInputDiscount.getText()));

                // if else for svc and gst (isChecked)
                if (cbSVC.isChecked()) {
                    totalAmt = totalAmt * 1.1; //(to cal total bill incl. 10% svc)

                } else if (cbGST.isChecked()) {
                    totalAmt = totalAmt * 1.07;

                } else if (cbSVC.isChecked() && cbGST.isChecked()) { //not able to cal both?
                    totalAmt = totalAmt * 1.17;

                } else {
                    totalAmt = totalAmt;

                }
                double totalBill = totalAmt - (totalAmt * (discount / 100)); //formula for cal total bill
                double splitTotalBill = totalBill / totalPax;

                //payment mode
                int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.radioButtonCash) {
                    tvDisplayMessage.setText(String.format("Total Bill: $%.2f", totalBill));
                    tvDisplayMessage2.setText(String.format("Each pays: $%.2f in cash", splitTotalBill));

                } else if (checkedRadioId == R.id.radioButtonPaynow) {
                    tvDisplayMessage.setText(String.format("Total Bill: $%.2f", totalBill));
                    tvDisplayMessage2.setText(String.format("Each pays: $%.2f via PayNow to 91110000", splitTotalBill));

                }
            }
        });

        btnResetDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put all variable names of inputs to clear when RESET is clicked
                etInputAmt.getText().clear();
                etInputPax.getText().clear();
                etInputDiscount.getText().clear();

                if (cbSVC.isChecked()) {
                    cbSVC.setChecked(false);  //setChecked(): from ws

                }
                if (cbGST.isChecked()) {
                    cbGST.setChecked(false);
                }
                if (rgPaymentCash.isChecked()) {
                    rgPaymentCash.setChecked(false);

                }
                if (rgPaymentPaynow.isChecked()) {
                    rgPaymentPaynow.setChecked(false);
                }
                tvDisplayMessage.setText(""); //(set to blank so message will clear too)
                tvDisplayMessage2.setText("");
            }
        });

    }
}
