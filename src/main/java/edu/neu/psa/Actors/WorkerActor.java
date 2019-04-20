package edu.neu.psa.Actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import edu.neu.psa.GA.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkerActor extends AbstractActor {

    static public Props props() {
        return Props.create(WorkerActor.class, () -> new WorkerActor());
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(CalculateFitness.class, request -> {
            Genotype genotype = request.genotype;
            HashMap<Integer, Team> teams = request.teams;
            genotype.createPhenoType(teams);
            sender().tell(new MasterActor.Result(genotype), getSelf());
            context().stop(getSelf());


        }).build();
    }


    static public class CalculateFitness {
        public final Genotype genotype;
        private final HashMap<Integer, Team> teams;

        public CalculateFitness(Genotype genotype, HashMap<Integer, Team> teams) {
            this.genotype = genotype;
            this.teams = teams;
        }
    }
}
