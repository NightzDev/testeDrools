package org.example.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

@Configuration
@EnableScheduling
public class DroolsConfig {

/*    @Bean
    public KieContainer kieContainer() {
        System.out.println("Refresh Rules");
        return KieServices.Factory.get().getKieClasspathContainer();
    }
*/

    public void refreshRules() {
        // Recarrega o KieContainer
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieServices kieServices = KieServices.Factory.get();
        ReleaseId releaseId = kieServices.newReleaseId( "org.example", "TesteDrools", "1.0-SNAPSHOT" );
        KieContainer kContainer = kieServices.newKieContainer( releaseId );
        KieScanner kScanner = kieServices.newKieScanner( kContainer );

        // Start the KieScanner polling the Maven repository every 10 seconds
        kScanner.start( 10000L );
    }
}
