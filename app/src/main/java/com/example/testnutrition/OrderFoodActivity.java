package com.example.testnutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testnutrition.models.Food;
import com.squareup.picasso.Picasso;

public class OrderFoodActivity extends AppCompatActivity
{
    TextView qtyThali,orderFoodName,orderFoodPrice,thaliType,thaliItemCount,thaliTime,orderFoodChapati,orderFoodChapatiType,orderFoodChapatiQty,orderFoodSpinnerType,orderFoodSpinnerQty,orderFoodCashBack,orderFoodCalories,orderFoodDescription;
    Button viewItems,addThali;
    LinearLayout hideLayout;
    ImageView orderFoodImage,incrementThali,removeThali,orderFoodChapatiAdd,orderFoodChapatiRemove,orderFoodSpinnerAdd,orderFoodSpinnerRemove;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_food_activity);
        initComponent();
        Intent in=getIntent();
        final Food f=(Food)in.getSerializableExtra("Food");
        String check = in.getStringExtra("isAdd");
        String qty = in.getStringExtra("qty");
        if(check.equals("true"))
        {
            addThali.setVisibility(View.GONE);
            hideLayout.setVisibility(View.VISIBLE);
        }
        qtyThali.setText(qty);
        addThali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThali.setVisibility(View.GONE);
                hideLayout.setVisibility(View.VISIBLE);
            }
        });
        incrementThali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q = Integer.parseInt(qtyThali.getText().toString().trim());
                q+=1;
                qtyThali.setText(""+q);
            }
        });
        removeThali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(qtyThali.getText().toString().trim());
                if(qty>1)
                {
                    qty-=1;
                    qtyThali.setText(""+qty);
                }
            }
        });
        orderFoodName.setText(f.getName());
        orderFoodPrice.setText("Thali Price : \u20B9 "+f.getPrice()+"/-");
        thaliType.setText("Thali Type : "+f.getType());
        thaliTime.setText("Minimum Time To Order : "+f.getTime()+"hrs");
        thaliItemCount.setText("No Of Items : "+f.getItemCount());
        Picasso.get().load(f.getImgUrl()).into(orderFoodImage);
     viewItems.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(OrderFoodActivity.this,ThaliItemsActivity.class).putExtra("thaliId",f.getThaliId()));
    }
});

       /*       // orderFoodImage.setImageResource(Integer.parseInt(f.getImgUrl()));

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        orderFoodSpinner.setSelection(1);
        orderFoodSpinner.setAdapter(adapter);


        orderFoodSpinnerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(orderFoodSpinnerQty.getText().toString());
                orderFoodSpinnerQty.setText(""+(++qty));
            }
        });
        orderFoodSpinnerRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(orderFoodSpinnerQty.getText().toString());
                if(qty>0)
                    orderFoodSpinnerQty.setText(""+(--qty));
            }
        });
        orderFoodChapatiAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(orderFoodChapatiQty.getText().toString());
                orderFoodChapatiQty.setText(""+(++qty));
            }
        });
        orderFoodChapatiRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(orderFoodChapatiQty.getText().toString());
                if(qty>0)
                    orderFoodChapatiQty.setText(""+(--qty));
            }
        });
*/
    }
    void initComponent()
    {
        orderFoodImage=findViewById(R.id.orderFoodImage);
        orderFoodName=findViewById(R.id.orderFoodName);
        orderFoodPrice=findViewById(R.id.OrderFoodItemPrice);
        viewItems=findViewById(R.id.viewItems);
        addThali=findViewById(R.id.addThali);
        thaliItemCount=findViewById(R.id.thaliItemCount);
        thaliTime=findViewById(R.id.thaliOrderTime);
        thaliType=findViewById(R.id.thaliType);
        incrementThali=findViewById(R.id.incrementThali);
        removeThali=findViewById(R.id.removeThali);
        qtyThali=findViewById(R.id.qtyThali);
        hideLayout=findViewById(R.id.hideLayout);

/*        orderFoodChapatiQty=findViewById(R.id.orderFoodChapatiQty);
        orderFoodChapatiAdd=findViewById(R.id.orderFoodChapatiAdd);
        orderFoodChapatiRemove=findViewById(R.id.orderFoodChapatiRemove);

        orderFoodSpinnerAdd=findViewById(R.id.orderFoodSpinnerAdd);
        orderFoodSpinner=findViewById(R.id.orderFoodSpinner);
        orderFoodSpinnerRemove=findViewById(R.id.orderFoodSpinnerRemove);
        orderFoodSpinnerQty=findViewById(R.id.orderFoodSpinnerQty);*/


    }
}
