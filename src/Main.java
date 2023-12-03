import java.io.*;
import java.net.URLConnection;
import java.util.Scanner;
import java.net.URL;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BankInformation.getCode();
        BankInformation.readDataFromSite();
    }
}

class BankInformation{
    private static String bankCode;

    public static void getCode(){
        Scanner scr = new Scanner(System.in);
        System.out.println("Please enter your bank account number\t:\t");
        bankCode = scr.next().substring(0,3);
    }
    public static void readDataFromSite() {
        try{
            URL url = new URL("https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String bank = null;
                while ((bank = br.readLine()) != null) {
                    String bankName = bank.substring(0,3), bankInfo= bank.substring(4).trim();
                    if (bankName.equals(bankCode)) {
                        System.out.println(bankInfo);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}