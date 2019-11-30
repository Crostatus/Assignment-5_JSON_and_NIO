import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.Random;

public class TransactionList {
    private static String[] listaCausali = {"Bonifico", "F24", "Accredito", "Bollettino", "PagoBancomat"};

    public static JsonArray getRandomTransactionList(){
        JsonArray randomTransactionList = newTransactionList();
        return randomTransactionList;
    }

    private static JsonArray newTransactionList(){
        String tipoCausale;
        Random numberGenerator = new Random();
        int transactionNumber = numberGenerator.nextInt(14) + 1;
        JsonObject movimento;
        JsonArray localTrList = new JsonArray();
        for(int i = 0; i < transactionNumber; i++){
            tipoCausale = getRandomCausale();
            movimento = new JsonObject();
            movimento.put("causale", tipoCausale);
            movimento.put("data", MyDate.getRandomDate());
            localTrList.add(movimento);
        }
        return localTrList;
    }

    private static String getRandomCausale(){
        Random numberGenerator = new Random();
        int index = numberGenerator.nextInt(5);
        return listaCausali[index];
    }

}
