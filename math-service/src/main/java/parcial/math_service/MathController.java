package parcial.math_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin("*")
public class MathController {


    @GetMapping("/lucasseq")
    public ResponseEntity<?> lucasSequence(@RequestParam("value") String valueStr){

        int value = Integer.parseInt(valueStr);
        List<Integer> list = new ArrayList<>();
        int cont = 0;
        List<Integer> sequence = lucasSequenceRecursive(list, cont, value);
        System.out.println(sequence);

        Map<String, String> response = new LinkedHashMap<>();
        response.put("operation", "Secuencia de lucas");
        response.put("input", valueStr);
        response.put("output", String.valueOf(sequence));
        return ResponseEntity.ok(response);
    }

    public List<Integer> lucasSequenceRecursive(List<Integer> list, int cont ,int n){
        if (cont > n ){
            return list;
        } else if (cont == 0) {
            list.add(2);
        } else if (cont == 1) {
            list.add(1);
        } else if (cont >= 2){
            list.add(list.get(cont-1) + list.get(cont-2));
        }
        cont++;
        return lucasSequenceRecursive(list, cont, n);
    }
}
