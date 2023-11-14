package org.example.service;

import org.drools.compiler.kie.builder.impl.KieBaseUpdater;
import org.drools.compiler.kie.builder.impl.KieBaseUpdaterImpl;
import org.example.config.DroolsConfig;
import org.kie.api.KieServices;
import org.kie.api.definition.rule.Rule;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class DroolsService {

    public void executeRules(int number) {
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kieContainer.getClassLoader());
        KieSession kieSession = kieContainer.newKieSession();
        Instant start = Instant.now();
        try {

            // Insira os fatos (dados) na sessão
            List<Integer> lista = new LinkedList<>();
            lista.add(10);
            lista.add(20);
            lista.add(5);
            lista.add(0);
            lista.add(3);
            lista.add(48);
            for (int a: lista){
                kieSession.insert(a);
                // Dispare as regras
                kieSession.fireAllRules();
            }
            kieSession.dispose();
            kieContainer.dispose();
            Instant end = Instant.now();
            Duration  elapsedTime = Duration.between(start, end);;

            System.out.println("Tempo de execução: " + elapsedTime.toMillis() + " milissegundos");
        } catch (Exception err ) {
            System.err.println("ERRO " + err);
        }
    }
}
