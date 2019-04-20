package edu.neu.psa.GA;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import edu.neu.psa.Actors.MasterActor;

import java.util.List;

public class EplGAParallel {


    public static void main(String[] args) {
        //GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.95, 4);
        //Population population = geneticAlgorithm.initPopulation(database);
        final ActorSystem actorSystem = ActorSystem.create("Epl-GA-System");
        ActorRef masterActor = actorSystem.actorOf(MasterActor.props());
        masterActor.tell(new MasterActor.Init(), null);


    }


}
