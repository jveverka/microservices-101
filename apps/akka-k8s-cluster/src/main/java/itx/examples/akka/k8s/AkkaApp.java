package itx.examples.akka.k8s;

import akka.actor.ActorSystem;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AkkaApp {

    private static final Logger LOG = LoggerFactory.getLogger(AkkaApp.class);

    public static void main(String[] args) {
        LOG.info("Akka k8s demo starting ...");
        Config config = ConfigFactory.load("akka-k8s-cluster.conf");
        ActorSystem actorSystem = ActorSystem.create("akka-k8s-cluster", config);
        LOG.info("Actor system created.");
        AkkaManagement management = AkkaManagement.get(actorSystem);
        management.start();
        LOG.info("AkkaManagement started.");
        ClusterBootstrap clusterBootstrap = ClusterBootstrap.get(actorSystem);
        clusterBootstrap.start();
        LOG.info("ClusterBootstrap started.");
        LOG.info("started.");
    }

}
