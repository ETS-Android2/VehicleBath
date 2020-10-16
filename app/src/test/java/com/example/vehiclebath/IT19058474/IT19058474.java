package com.example.vehiclebath.IT19058474;

import com.example.vehiclebath.adminViewAllAppointments;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IT19058474 {

    private static adminViewAllAppointments adminViewAllAppointments;
    public static double result1, result2;

    @BeforeClass
    public static void setAdminViewAllAppointment() {
        adminViewAllAppointments = new adminViewAllAppointments();
    }

    @Before
    public void setup(){

        result1 =adminViewAllAppointments.findTotIncome(5);
    }

    @Test
    public void calTot() {
        assertEquals(1500.0,result1,0.1);
    }

    @After
    public void clearResultValue() {
        result1 = 0;
    }
    @AfterClass
    public static void clearObject() {
        adminViewAllAppointments = null;
    }

}
