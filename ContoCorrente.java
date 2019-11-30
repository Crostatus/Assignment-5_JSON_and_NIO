import com.github.cliftonlabs.json_simple.JsonObject;

public class ContoCorrente {
    private static int i = 1;

    public static JsonObject getRandomContoCorrente(){
        JsonObject contoCorrente = new JsonObject();
        contoCorrente.put("nome", "Cliente " + i);
        i++;
        contoCorrente.put("lista movimenti", TransactionList.getRandomTransactionList());
        return contoCorrente;
    }


}
