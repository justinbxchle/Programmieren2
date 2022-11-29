import java.math.BigInteger;

public class Main {

    // Fill accountNr to length 10
    static public String FillAccountNumber(String number) {
        int length = String.valueOf(number).length();
        if (length < 10) {
            for (int i = 0; i < (10 - length); i++)
                number = "0" + number;
        }
        return number;
    }

    //Convert country to number sequence
    static public String CountryCode(String letters) {
        String sequence = "";
        char[] lettersA = letters.toCharArray();
        for (int i = 0; i < lettersA.length; i++) {
            sequence = sequence + String.valueOf(((int) lettersA[i]) - 55);
        }
        sequence = sequence + "00";
        return sequence;
    }

    //Link Bank Code and Account Number
    static public String Link(String country, String bankCode, String accountNumber) {
        accountNumber = FillAccountNumber(accountNumber);
        String link2 =  bankCode + accountNumber + CountryCode(country);
        return link2;
    }

    //Calculate Check Number
    static public String CheckNumber(String country, String bankCode, String accountNumber) {
        //Calculating Modulo
        BigInteger const97 = new BigInteger("97");
        BigInteger const98 = new BigInteger("98");
        String number = Link(country, bankCode, accountNumber);
        BigInteger link = new BigInteger(number);
        BigInteger modulo = link.mod(const97);
        modulo = const98.subtract(modulo);
        //Check Number
        String checkNr = modulo.toString();
        if (String.valueOf(checkNr).length() == 1) {
            checkNr = "0" + checkNr;
        }
        return checkNr;
    }

    static String Iban(String country, String bankCode, String accountNumber) {
        String iban =  country +  CheckNumber(country, bankCode, accountNumber) + bankCode + FillAccountNumber(accountNumber);
        char[] ibanArray = iban.toCharArray();
        char[] ibanArraySpace = new char[ibanArray.length + 5];
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < ibanArraySpace.length; i++) {
            if (count1 == 4) {
                ibanArraySpace[i] = ' ';
                System.out.println("hllo");
                count1 = 0;
                count2++;
                i++;
            }
            System.out.println(i);
            ibanArraySpace[i] = ibanArray[i-count2];
            iban = String.valueOf(ibanArraySpace);
            count1++;
        }
        return iban;
    }



    //Main Method
    public static void main(String[] args) {
        String country = args[0];
        String bankCode = args[1];
        String accountNumber = args[2];

        System.out.println(Iban(country,bankCode,accountNumber));



    }
}