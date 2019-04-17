import edu.neu.psa.GA.Database;
import edu.neu.psa.GA.Individual;
import junit.framework.Assert;
import org.junit.Test;

public class TimetableTest {


    @Test
    public void testClashesSixTeam() {
        Database database = initializeDatabaseTeam6();
        int[] chromosomes = new int[]{5, 1, 6, 4, 3, 2, 3, 5, 6, 6, 2, 4, 6, 1, 4, 2, 5, 3, 2, 3, 6, 5, 1, 4, 1, 3, 4, 6, 2, 5, 1, 5, 2, 6, 3, 4, 5, 2, 4, 1, 3, 6, 6, 3, 5, 4, 1, 2, 3, 1, 4, 5, 6, 2, 2, 1, 5, 6, 4, 3};
        //1,6,5,4,2,3,2,1,5,3,6,4,1,2,3,4,5,6,1,3,2,4,6,5,5,1,4,2,3,6,2,6,1,4,3,5,2,5,3,1,4,6,5,2,6,3,4,1,6,2,1,5,4,3,6,1,4,5,3,2,
        //1,6,5,4,2,3,2,1,5,3,6,4,1,2,3,4,5,6,1,3,2,4,6,5,5,1,4,2,3,6,2,6,1,4,3,5,2,5,3,1,4,6,5,2,6,3,4,1,6,2,1,5,4,3,2,2,4,5,3,2,
        // 8,4,2,5,1,7,6,3,5,2,1,8,6,4,3,7,8,2,5,1,4,3,7,6,1,6,5,4,7,2,8,3,6,7,4,5,8,1,3,2,2,1,3,4,8,6,7,5,1,5,4,2,6,8,7,3,2,8,5,6,7,4,1,3,1,4,6,2,7,8,4,4,6,5,1,2,4,7,3,8,8,5,3,6,7,1,2,4,8,7,4,1,2,6,3,5,2,7,3,1,5,8,4,6,6,1,5,7,2,3,4,8,
        Individual individual = new Individual(chromosomes);
        database.createSeasonSchedule(individual);
        int clashes = database.calculateClashes();
        double fitness = 1 / (double) (clashes + 1);
        Assert.assertEquals(1, fitness);


    }

    @Test
    public void testClashesEightTeam() {
        Database database = initializeDatabaseTeam6();
        int[] chromosomes = new int[]{3, 6, 2, 5, 1, 4, 5, 6, 3, 2, 4, 1, 2, 1, 4, 5, 6, 3, 5, 3, 6, 1, 2, 4, 5, 4, 1, 3, 2, 6, 5, 1, 4, 6, 2, 3, 6, 5, 4, 2, 3, 1, 1, 5, 3, 4, 6, 2, 5, 2, 4, 3, 1, 6, 3, 5, 1, 2, 6, 4};
        //1,6,5,4,2,3,2,1,5,3,6,4,1,2,3,4,5,6,1,3,2,4,6,5,5,1,4,2,3,6,2,6,1,4,3,5,2,5,3,1,4,6,5,2,6,3,4,1,6,2,1,5,4,3,6,1,4,5,3,2,
        //1,6,5,4,2,3,2,1,5,3,6,4,1,2,3,4,5,6,1,3,2,4,6,5,5,1,4,2,3,6,2,6,1,4,3,5,2,5,3,1,4,6,5,2,6,3,4,1,6,2,1,5,4,3,2,2,4,5,3,2,
        // 8,4,2,5,1,7,6,3,5,2,1,8,6,4,3,7,8,2,5,1,4,3,7,6,1,6,5,4,7,2,8,3,6,7,4,5,8,1,3,2,2,1,3,4,8,6,7,5,1,5,4,2,6,8,7,3,2,8,5,6,7,4,1,3,1,4,6,2,7,8,4,4,6,5,1,2,4,7,3,8,8,5,3,6,7,1,2,4,8,7,4,1,2,6,3,5,2,7,3,1,5,8,4,6,6,1,5,7,2,3,4,8,
        Individual individual = new Individual(chromosomes);
        database.createSeasonSchedule(individual);
        int clashes = database.calculateClashes();
        double fitness = 1 / (double) (clashes + 1);

        Assert.assertEquals(1.0, fitness);


    }

    public static Database initializeDatabaseTeam6() {
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


    public static Database initializeDatabaseTeam8() {
        Database database = new Database();
        database.addTeam(1, "Manchester United");
        database.addTeam(2, "Manchester City");
        database.addTeam(3, "Chelsea");
        database.addTeam(4, "Liverpool");
        database.addTeam(5, "Tottenham");
        database.addTeam(6, "Arsenal");
        database.addTeam(7, "Everton");
        database.addTeam(8, "Wolves");
//        database.addTeam(9, "Leicester City");
//        database.addTeam(10, "Southampton");
        return database;

    }

}
