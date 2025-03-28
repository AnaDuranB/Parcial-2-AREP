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
        List<Integer> sequence = lucasSequenceRecursive(value);
        System.out.println(sequence);

        Map<String, String> response = new LinkedHashMap<>();
        response.put("operation", "Secuencia de lucas");
        response.put("input", valueStr);
        response.put("output", String.valueOf(sequence));
        return ResponseEntity.ok(response);
    }

    public List<Integer> lucasSequenceRecursive(int n){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < (n+1); i++){
            if (i == 0){
                list.add(2);
            } else if (i == 1) {
                list.add(1);
            } else {
               list.add(list.get(i-1) + list.get(i-2));
            }
        }
        return list;
    }
}
