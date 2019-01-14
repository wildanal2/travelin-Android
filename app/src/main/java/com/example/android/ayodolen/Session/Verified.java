package com.example.android.ayodolen.Session;

import android.view.View;
import android.widget.EditText;

public class Verified {


    public boolean cekLogin(EditText email,EditText pass){
        if (emailHpLength(email)){
            if (passwordLength(pass)){
                return true;
            }else {
                return false;
            }

        }else {
            return false;
        }
    }

    public boolean cekDaftar(EditText nama,EditText email,EditText nohp,EditText pass){
        if (namaLength(nama)){
            if (emailLength(email)) {
                if (noHpLength(nohp)){
                    if (passwordLength(pass)) return true;
                    else return false;
                }else return false;
            }else return false;
        }else return false;
    }




    public boolean passwordLength(EditText etPass){
        if (etPass.getText().toString().trim().length()>=6){
            return true;
        }else {
            etPass.setError("Password harus lebih dari 6 karakter");
            etPass.requestFocus();
            return false;
        }
    }

    public boolean emailHpLength(EditText etEmail){
        if (etEmail.getText().toString().trim().length()>=8){
            return true;
        }else if (etEmail.getText().toString().trim().length()==0){
            etEmail.setError("Email / Nomor Hp Tidak boleh Kosong");
            etEmail.requestFocus();
            return false;
        }else {
            etEmail.setError("Email/NoHp harus lebih dari 8 karakter");
            etEmail.requestFocus();
            return false;
        }
    }

    public boolean noHpLength(EditText etNoHp){
        if (etNoHp.getText().toString().trim().length()>=7){
            return true;
        }else if (etNoHp.getText().toString().trim().length()==0){
            etNoHp.setError("Nomor Hp Tidak boleh Kosong");
            etNoHp.requestFocus();
            return false;
        }else {
            etNoHp.setError("Nomor Hp harus lebih dari 7 karakter");
            etNoHp.requestFocus();
            return false;
        }
    }

    public boolean emailLength(EditText email){
        if (email.getText().toString().trim().length()>=8){
            return true;
        }else if (email.getText().toString().trim().length()==0){
            email.setError("Email Tidak boleh Kosong");
            email.requestFocus();
            return false;
        }else {
            email.setError("Email harus lebih dari 8 karakter");
            email.requestFocus();
            return false;
        }
    }

    public boolean namaLength(EditText nama){
        if (nama.getText().toString().trim().length()>=3){
            return true;
        }else if (nama.getText().toString().trim().length()==0){
            nama.setError("Nama Tidak boleh Kosong");
            nama.requestFocus();
            return false;
        }else {
            nama.setError("Nama harus lebih dari 3 karakter");
            nama.requestFocus();
            return false;
        }
    }




}
