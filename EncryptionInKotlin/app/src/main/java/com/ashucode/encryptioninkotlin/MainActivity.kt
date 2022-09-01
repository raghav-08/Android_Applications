package com.ashucode.encryptioninkotlin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.security.KeyPairGenerator
import java.security.Signature
import javax.crypto.Cipher

class MainActivity : AppCompatActivity() {
    lateinit var normalTxt : EditText
    lateinit var encryptText : EditText
    lateinit var encryptCode : TextView
    lateinit var decryptText : TextView
    lateinit var encryptBtn : Button
    lateinit var copy : Button
    lateinit var decryptBtn : Button
    lateinit var clear : Button
    lateinit var cbm : ClipboardManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cbm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        normalTxt = findViewById(R.id.normaltext)
        encryptCode = findViewById(R.id.encryptedcode)
        encryptText = findViewById(R.id.encryptText)
        decryptText = findViewById(R.id.decryptText)

        encryptBtn = findViewById(R.id.btn_encrypt)
        copy = findViewById(R.id.btn_copy)
        decryptBtn = findViewById(R.id.btn_Dcrypt)
        clear = findViewById(R.id.btn_clear)

        encryptBtn.setOnClickListener()
        {
            try {
                val sign = Signature.getInstance("SHA256withRSA")
                val keypairGen = KeyPairGenerator.getInstance("RSA")
                keypairGen.initialize(2048)
                val pair = keypairGen.generateKeyPair()
                val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipher.init(Cipher.ENCRYPT_MODE, pair.public)
                val text = normalTxt.text.toString()
                val input = text.toByteArray()
                cipher.update(input)
                val cipherText = cipher.doFinal()
                encryptCode.setText(String(cipherText))
            }
            catch (e:Exception)
            {
                System.out.print(e);
            }
        }
        copy.setOnClickListener()
        {
            val data = encryptCode.text.toString().trim()
            if (!data.isEmpty())
            {
                val temp = ClipData.newPlainText("text",data)
                cbm.setPrimaryClip(temp)
                Toast.makeText(this@MainActivity, "Copied", Toast.LENGTH_SHORT).show()
            }
        }
        decryptBtn.setOnClickListener()
        {
            try
            {
                val sign = Signature.getInstance("SHA256withRSA")
                val keyPairGen = KeyPairGenerator.getInstance("RSA")
                keyPairGen.initialize(2048)
                val pair = keyPairGen.generateKeyPair()
                val publicKey = pair.public
                val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipher.init(Cipher.ENCRYPT_MODE, publicKey)
                val text2: String = normalTxt.getText().toString()
                val input = text2.toByteArray()
                cipher.update(input)
                val cipherText = cipher.doFinal()
                cipher.init(Cipher.DECRYPT_MODE, pair.private)
                val decipheredText = cipher.doFinal(cipherText)
                decryptText.setText(String(decipheredText))
            }
            catch (ex: java.lang.Exception)
            {
                print(ex)
            }
        }
        clear.setOnClickListener()
        {
            val txt = encryptText.text.toString()
            if(txt.isEmpty())
                Toast.makeText(this@MainActivity, "Already Empty !!!", Toast.LENGTH_SHORT).show()
            else
            {
                encryptText.setText("")
                decryptText.setText("")
            }

        }
    }
}