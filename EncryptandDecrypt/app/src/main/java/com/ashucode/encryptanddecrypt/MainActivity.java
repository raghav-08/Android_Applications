package com.ashucode.encryptanddecrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {

    EditText normaltext,encrpytedtext;
    TextView encryptedcode, result;
    Button Encrypt,copy,Decrypt,clear;
    ClipboardManager cpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a clipboard manager variable to copy text
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        //Encryption material
        normaltext = findViewById(R.id.normaltext);
        encryptedcode = findViewById(R.id.encryptedcode);
        Encrypt = findViewById(R.id.btn_encrypt);
        copy = findViewById(R.id.btn_copy);

        //Decryption material
        encrpytedtext = findViewById(R.id.dcrypt_etText);
        result = findViewById(R.id.tv_result);
        Decrypt = findViewById(R.id.btn_Dcrypt);
        clear = findViewById(R.id.btn_clear);

        Encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Creating a Signature object
                    Signature sign = Signature.getInstance("SHA256withRSA");

                    //Creating KeyPair generator object
                    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

                    //Initializing the key pair generator
                    keyPairGen.initialize(2048);

                    //Generating the pair of keys
                    KeyPair pair = keyPairGen.generateKeyPair();

                    //Creating a Cipher object
                    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

                    //Initializing a Cipher object
                    cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());

                    //Adding data to the cipher
                    String text = normaltext.getText().toString();
                    byte[] input = text.getBytes();
                    cipher.update(input);

                    //encrypting the data
                    byte[] cipherText = cipher.doFinal();
                    encryptedcode.setText(new String(cipherText, "UTF8"));
//                    System.out.println(new String(cipherText, "UTF8"));
                }
                catch (Exception e)
                {
                    System.out.print(e);
                }
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = encryptedcode.getText().toString().trim();

                // check if the textview is not empty
                if (!data.isEmpty()) {

                    // copy the text in the clip board
                    ClipData temp = ClipData.newPlainText("text", data);
                    cpb.setPrimaryClip(temp);

                    // display message that the text has been copied
                    Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Creating a Signature object
                    Signature sign = Signature.getInstance("SHA256withRSA");

                    //Creating KeyPair generator object
                    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

                    //Initializing the key pair generator
                    keyPairGen.initialize(2048);

                    //Generate the pair of keys
                    KeyPair pair = keyPairGen.generateKeyPair();

                    //Getting the public key from the key pair
                    PublicKey publicKey = pair.getPublic();

                    //Creating a Cipher object
                    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

                    //Initializing a Cipher object
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);

                    //Add data to the cipher
                    String text2 = normaltext.getText().toString();
                    byte[] input = text2.getBytes();
                    cipher.update(input);

                    //encrypting the data
                    byte[] cipherText = cipher.doFinal();

                    //Initializing the same cipher for decryption
                    cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

                    //Decrypting the text
                    byte[] decipheredText = cipher.doFinal(cipherText);
                    result.setText(new String(decipheredText));
                }
                catch (Exception ex)
                {
                    System.out.print(ex);
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = encrpytedtext.getText().toString();
                if(text.isEmpty())
                    Toast.makeText(MainActivity.this, "Already Empty !!!", Toast.LENGTH_SHORT).show();
                else
                    encrpytedtext.setText("");
            }
        });

    }
}