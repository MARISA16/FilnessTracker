package co.tiagoaguiar.codelab.myapplication;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {
    private EditText editHeight;
    private EditText  editWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        editHeight = findViewById(R.id.edit_imc_height);
        editWeight = findViewById(R.id.edit_imc_wieght);
        Button  btnSend = findViewById(R.id.btn_imc_send);
        btnSend.setOnClickListener(view -> {
            if (!validate()){ // primeira ivalidei e depois que fiz o sucesso
                Toast.makeText(ImcActivity.this,R.string.fields_messages,Toast.LENGTH_SHORT).show();

                return;
            }
            // sucesso ou parte logica
            String sHeight = editHeight.getText().toString();
            String sWeight = editWeight.getText().toString();
            int height = Integer.parseInt(sHeight);
            int weight = Integer.parseInt(sWeight);

            double result = calculateImc(height,weight);
            Log.d("TESTE","resultado:" + result);
            int imcResponseId = imcResponse(result);
            AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                    .setTitle(getString(R.string.imc_response,result))
                    .setMessage(imcResponseId)
                    .setPositiveButton(android.R.string.ok, (dialog1, which) -> {

                    })
                    .create();
            dialog.show();
            // escoder o teclado
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editWeight.getWindowToken(),0);
            imm.hideSoftInputFromWindow(editHeight.getWindowToken(),0);


        });

    }
    @StringRes // permite que o desenvolvedor  apenas  devolva como resultado o retorn desse inteiro e precisamente ser um arquivo de REs
    private int imcResponse(double imc){
        if (imc<15)
            return R.string.imc_severely_low_weight;
        else if (imc<16)
            return R.string.imc_very_low_weight;
        else if (imc<18.5)
            return R.string.imc_low_weight;
        else if (imc<25)
            return R.string.normal;
        else if (imc<30)
            return R.string.imc_high_weight;
        else if (imc<35)
            return R.string.imc_so_higt_eight;
        else if (imc<40)
            return R.string.imc_severely_high_weight;
        else
            return R.string.imc_extreme_weiight;
    }
    //calcular a conta
    private double calculateImc(int height,int weight){
        //peso/altura * altura
        return  weight / (((double) height / 100) * ((double) height / 100));

    }

    private boolean validate(){
        return  (!editHeight.getText().toString().startsWith("0")  // para que a condição seja valida não deve começar com zero
                && !editWeight.getText().toString().startsWith("0") // não deve começar com zero altura
                && !editWeight.getText().toString().isEmpty() // também não devem serem vazios quando eu partar o botão de enviar
                && !editWeight.getText().toString().isEmpty());

    }

}