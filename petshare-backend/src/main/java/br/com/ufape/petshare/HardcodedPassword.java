package br.com.ufape.petshare;

public class HardcodedPassword {
    public static void main(String[] args) {
        String password = "admin123"; // 🚨 Vulnerável: Senha hardcoded!
        System.out.println("Senha: " + password);
    }
}
