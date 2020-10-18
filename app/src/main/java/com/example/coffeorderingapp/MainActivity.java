package com.example.coffeorderingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void submitOrder(View view){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ordering a Coffee");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public String orderSummary(){
        int price_of_cofee = 0;

        CheckBox WippedCreamChecked = findViewById(R.id.WipppedCreamcheckBox);
        String cream = (WippedCreamChecked.isChecked()) ? "is " : "is not ";
        if (WippedCreamChecked.isChecked()){
            price_of_cofee += 1 * quantity;
        }

        CheckBox ChocolateChecked = findViewById(R.id.chocolateCheckbox);
        String chocolate = (ChocolateChecked.isChecked()) ? "is " : "is not ";
        if (ChocolateChecked.isChecked()){
            price_of_cofee += 2 * quantity;
        }

        EditText EnterName = findViewById(R.id.EnterYourName);
        String name = EnterName.getText().toString();

        price_of_cofee += 5* quantity;

        String orderMessage = " Name: " + name;
        orderMessage += "\n Wipped Cream "+ cream + "added";
        orderMessage += "\n Chocolate "+ chocolate + "added";
        orderMessage += "\n Quantity: " + quantity;
        orderMessage += "\n Total Price: $" + price_of_cofee ;
        orderMessage += "\n Thank YOU!!";
        return orderMessage;

    }

    public void Increment(View view ){
        if(quantity < 100)
            quantity++;
        else
            Toast.makeText(getApplicationContext()," You cannot order more than 100 Cups of coffee ",Toast.LENGTH_SHORT).show();
        display(quantity);

    }

    public void decrement(View view ){
        if(quantity > 1)
            quantity--;
        else
            Toast.makeText(getApplicationContext()," You cannot order less than 100 Cups of coffee",Toast.LENGTH_SHORT).show();
        display(quantity);
    }


}
