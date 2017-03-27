package models;

import play.*;
import play.jobs.*;
import play.test.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        Fixtures.delete();
        // Check if the database is empty
        if(Company.count() == 0) {
            Fixtures.loadModels("initial-data.yml");
        }

    }
}
