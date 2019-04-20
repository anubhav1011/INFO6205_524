package edu.neu.psa.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import edu.neu.psa.GA.Database;
import edu.neu.psa.GA.GeneticAlgorithm;
import edu.neu.psa.GA.Genotype;
import edu.neu.psa.GA.Population;

public class MasterActor extends AbstractActor {

    Population currentPopulation;

    private final int populationSize;

    public MasterActor(int populationSize) {
        this.populationSize = populationSize;
    }

    static public Props props() {
        return Props.create(MasterActor.class, () -> new MasterActor(100));
    }

    /*
     * Messages that can be received by the MasterActor
     *
     * */
    static public class Init {

        public Init() {
        }
    }

    static public class Result {

        public final Genotype genotype;

        public Result(Genotype genotype) {
            this.genotype = genotype;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Init.class, init -> {
            executeInitLogic();

        }).match(Result.class, result -> {
        }).build();
        //return null;
    }


    public void evaluateResult(Genotype genotype) {
        this.currentPopulation.addGenotype(genotype);
        if (this.currentPopulation.populationSize() == this.populationSize){




        }
    }


    public void executeInitLogic() {
        //Create Initial Population
        Database database = initializeDatabase();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(this.populationSize, 0.01, 0.95, 4);
        Population population = geneticAlgorithm.initPopulation(database);
        this.currentPopulation = population;
        for (Genotype genotype : population.getGenotypes()) {
            WorkerActor.CalculateFitness calculateFitness = new WorkerActor.CalculateFitness(genotype, database.getTeams());
            ActorRef workerActor = getContext().actorOf(WorkerActor.props(), "Generation-1" + "-Child-" + population.getGenotypes().indexOf(genotype));
            workerActor.tell(calculateFitness, getSelf());
        }
        this.currentPopulation.getGenotypes().clear();

    }

    public static Database initializeDatabase() {
        Database database = new Database();
        database.addTeam(1, "Manchester United");
        database.addTeam(2, "Manchester City");
        database.addTeam(3, "Chelsea");
        database.addTeam(4, "Liverpool");
        database.addTeam(5, "Tottenham");
        database.addTeam(6, "Arsenal");
//        database.addTeam(7, "Everton");
//        database.addTeam(8, "Wolves");
//        database.addTeam(9, "Leicester City");
//        database.addTeam(10, "Southampton");
        return database;

    }

}
