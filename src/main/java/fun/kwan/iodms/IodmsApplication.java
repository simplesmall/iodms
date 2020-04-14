package fun.kwan.iodms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("fun.kwan.iodms.mapper")
@SpringBootApplication
public class IodmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IodmsApplication.class, args);
    }

}
