package com.example.a38_buivanquang_btlap4_day4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etuser,etpass;
    Button btndangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etpass=findViewById(R.id.etpass);
        etuser=findViewById(R.id.etuser);
        btndangnhap=findViewById(R.id.btndangnhap);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=etuser.getText().toString();
                String pass=etpass.getText().toString();
                //không nhập user && pass
                if (pass.equals("")){
                    Toast.makeText(getBaseContext(),"ban chua nhap pass!!!",Toast.LENGTH_LONG).show();
                    return;
                }

                if (user.equals("")){
                    Toast.makeText(getBaseContext(),"ban chua nhap user!!!",Toast.LENGTH_LONG).show();
                    return;
                }
                //độ dai it nhat 6
                for (int i=0;i<pass.length();i++)
                {
                    if (pass.length()<6){
                        Toast.makeText(getBaseContext(),"do dai it nhat cua pass la 6 ki tu!!!",Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                //bao gom chua hoa && chu thuong &&

                int chuHoa=0, chuThuong=0, so=0, kitudb=0;
                for(int i=0;i<pass.length();i++){
                    if(pass.charAt(i)>='a' && pass.charAt(i)<='z') chuThuong++;
                    else if(pass.charAt(i)>='A' && pass.charAt(i)<='Z') chuHoa++;
                    else if(pass.charAt(i)>='0' && pass.charAt(i)<='9') so++;
                    else kitudb++;
                }
                if(chuHoa>0 && chuThuong>0 && so>0 && kitudb>0){
                    Toast.makeText(getBaseContext(),"Pass nhap dung",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"phai co it nhat 1 chu hoa, 1 chu thuong, 1 ki tu dac biet, 1 so!!!",Toast.LENGTH_LONG).show();
                }

                Intent intent=new Intent(getBaseContext(),Main2Activity.class);
                startActivity(intent);



            }
        });
    }
}
