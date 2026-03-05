package magazyn;

import magazyn.repository.WarehouseDao;
import magazyn.repository.ProductDao;
import magazyn.repository.mem.MemWarehouseDao;
import magazyn.repository.mem.MemProductDao;
import magazyn.model.Warehouse;
import magazyn.service.WarehouseService;
import magazyn.service.impl.WarehouseServiceBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Aktywuje autokonfigurację i skanowanie pakietów [cite: 70, 73]
public class WarehouseAppMain {

    public static void main(String[] args) {
        // Uruchomienie aplikacji – Spring sam stworzy kontekst [cite: 76, 79]
        SpringApplication.run(WarehouseAppMain.class, args);
    }
}