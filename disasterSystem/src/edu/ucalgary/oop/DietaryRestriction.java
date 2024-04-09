package edu.ucalgary.oop;

public enum DietaryRestriction {
    AVML, 
    DBML, 
    GFML, 
    KSML, 
    LSML, 
    MOML, 
    PFML, 
    VGML, 
    VJML  
}

// The command used to run a single test file
// java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.oop.DisasterVictimTest

// The command used to compile all files at once
// javac -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar edu/ucalgary/oop/*.java

// The command used to run all test files at once
// java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.oop.DisasterVictimTest edu.ucalgary.oop.FamilyRelationTest edu.ucalgary.oop.InquirerTest edu.ucalgary.oop.InteractionLogTest edu.ucalgary.oop.LocationTest edu.ucalgary.oop.MedicalRecordTest edu.ucalgary.oop.ReliefServiceTest edu.ucalgary.oop.SupplyTest

// The command used to run main without database
// java edu.ucalgary.oop.DisasterSystem

// The command used to run main system and interface
// java -cp .;postgresql-42.7.3.jar;junit-4.13.2.jar;hamcrest-core-1.3.jar edu.ucalgary.oop.DisasterSystem

// The commands used to make the database start at MAX ID
// SELECT setval('inquirer_id_seq', (SELECT MAX(id) FROM INQUIRER));
// SELECT setval('inquiry_log_id_seq', (SELECT MAX(id) FROM INQUIRY_LOG));

// The commands used to reset the database
// DELETE FROM inquiry_log WHERE inquirer > 6;
// DELETE FROM inquirer WHERE id > 6;
