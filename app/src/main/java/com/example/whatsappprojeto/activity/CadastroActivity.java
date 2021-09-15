package com.example.whatsappprojeto.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappprojeto.R;
import com.example.whatsappprojeto.config.ConfiguracaoFirebase;
import com.example.whatsappprojeto.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {
    private TextInputEditText editNome, editEmail, editSenha;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editLoginEmail);
        editSenha = findViewById(R.id.editLoginSenha);
    }
    public void cadastrarUsuario(Usuario usuario){
    autenticacao = ConfiguracaoFirebase.getFirebaseAuthenticacao();
    autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()

    ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            String excecao = "";
            try{
                throw task.getException();
            }catch (FirebaseAuthWeakPasswordException e){
                excecao = "Digite uma senha mais forte!";
            }catch (FirebaseAuthInvalidCredentialsException e){
                excecao = "Por favor, digite um e-mail válido";
            }catch (FirebaseAuthUserCollisionException e){
                excecao = "Esta conta já foi cadastrada";
            }catch (Exception e){
                excecao = "Erro ao cadastrar usuário:" + e.getMessage();
                e.printStackTrace();
            }
            Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
        }
        }
    });

    }
    public void validarCadastroUsuario(View view) {
        String TextoNome = editNome.getText().toString();
        String TextoEmail = editEmail.getText().toString();
        String TextoSenha = editSenha.getText().toString();

        if (!TextoNome.isEmpty()) {//verificaNome

            if (!TextoEmail.isEmpty()) {//verificaEmail

                if (!TextoSenha.isEmpty()) {

                    Usuario usuario = new Usuario();
                    usuario.setNome(TextoNome);
                    usuario.setEmail(TextoEmail);
                    usuario.setSenha(TextoSenha);
                    cadastrarUsuario(usuario);

                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(CadastroActivity.this, "Preencha o Email!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();

        }
    }





}