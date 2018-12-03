package wojtek.net.securityFun;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CosTamRest {

    @GetMapping("/secForAdmin")
    public ResponseEntity<String> cosTam(){

        return ResponseEntity.ok("cos tam secured ADMIN");

    }

    @GetMapping("/secAny")
    public ResponseEntity<String> cosTam1(){

        return ResponseEntity.ok("cos tam secured ANY");

    }

    @GetMapping("/noSec")
    public ResponseEntity<String> cosTam0(){


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String wynik = encoder.encode("wojtek");

        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + wynik);

        return ResponseEntity.ok("cos tam NO secured");

    }

//    @GetMapping("/out")
//    public ResponseEntity<String> cosTam2(){
//
//        return ResponseEntity.ok("cos tam logout");
//
//    }
}
