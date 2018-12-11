package location;

import combat.ship.RoomFunction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static testing_tools.SampleObjects.*;

public class DepartmentTest {
    private Department tester;

    @Before
    public void setUp() {
        tester = new Department(createSampleWeapons(), createSampleUpgrades(RoomFunction.CROWS_NEST, 2),
                createSampleResourceStock(1));
    }

    @Test
    public void buyWeapon() {
    }

    @Test
    public void buyRoomUpgrade() {
    }

    @Test
    public void buyResource() {
    }
}