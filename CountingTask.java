import com.github.cliftonlabs.json_simple.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public class CountingTask implements Runnable {
    AtomicLong bonifici;
    AtomicLong accrediti;
    AtomicLong bollettini;
    AtomicLong f24;
    AtomicLong pagoBancomat;
    JSONObject contoCorrente;
    Counter contatore;

    public CountingTask(JSONObject cliente, Counter contatore){
        this.contoCorrente = cliente;
        this.contatore = contatore;
    }

    public void run(){
        JSONArray o = (JSONArray) contoCorrente.get("lista movimenti");
        Iterator <JSONObject> iterator = o.iterator();

        while(iterator.hasNext()){
            String movimento = (String) iterator.next().get("causale");
            contatore.increment(movimento);
        }


    }




    }



