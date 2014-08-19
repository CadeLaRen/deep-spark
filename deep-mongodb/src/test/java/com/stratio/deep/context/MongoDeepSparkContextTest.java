package com.stratio.deep.context;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.DeepMongoRDD;
import org.apache.spark.rdd.RDD;
import org.testng.annotations.Test;

import com.stratio.deep.config.CellDeepJobConfigMongoDB;
import com.stratio.deep.config.EntityDeepJobConfigMongoDB;
import com.stratio.deep.config.GenericDeepJobConfigMongoDB;
import com.stratio.deep.entity.Cells;
import com.stratio.deep.exception.DeepGenericException;
import com.stratio.deep.rdd.mongodb.MongoCellRDD;
import com.stratio.deep.rdd.mongodb.MongoEntityRDD;
import com.stratio.deep.rdd.mongodb.MongoJavaRDD;
import com.stratio.deep.testentity.BookEntity;


/**
 * Created by rcrespo on 16/07/14.
 */
@Test
public class MongoDeepSparkContextTest {

    private Logger log = Logger.getLogger(getClass());

    @Test()
    public void mongoRDDTest() {
        DeepSparkContext sc = new DeepSparkContext("local", "DeepSparkContextTest");

        RDD deepMongoRDD = sc.createRDD(new CellDeepJobConfigMongoDB());

//        assertTrue(deepMongoRDD instanceof MongoCellRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoEntityRDD);


        deepMongoRDD = sc.createRDD(new EntityDeepJobConfigMongoDB(BookEntity.class));

//        assertTrue(deepMongoRDD instanceof MongoEntityRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoCellRDD);


        JavaRDD<Cells> javaRDDCells = sc.createJavaRDD(new CellDeepJobConfigMongoDB());

        assertNotNull(javaRDDCells);

        assertTrue(javaRDDCells instanceof JavaRDD);

        assertTrue(javaRDDCells instanceof MongoJavaRDD);


        JavaRDD<BookEntity> javaRDDEntity = sc.createJavaRDD(new EntityDeepJobConfigMongoDB(BookEntity.class));

        assertNotNull(javaRDDEntity);

        assertTrue(javaRDDEntity instanceof JavaRDD);

        assertTrue(javaRDDEntity instanceof MongoJavaRDD);
//
//        try {
//            DeepMongoRDD failRDD = sc.createRDD(new GenericDeepJobConfigMongoDB());
//            fail();
//        } catch (DeepGenericException e) {
//            log.info("Correctly catched DeepGenericException: " + e.getLocalizedMessage());
//        }


        sc.stop();


    }

    @Test
    public void testInstantiationBySparkContext() {
        DeepSparkContext sc = new DeepSparkContext(new SparkContext("local", "myapp1", new SparkConf()));

        assertNotNull(sc);

        RDD deepMongoRDD = sc.createRDD(new CellDeepJobConfigMongoDB());
//
//        assertTrue(deepMongoRDD instanceof MongoCellRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoEntityRDD);


        deepMongoRDD = sc.createRDD(new EntityDeepJobConfigMongoDB(BookEntity.class));
//
//        assertTrue(deepMongoRDD instanceof MongoEntityRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoCellRDD);

        sc.stop();
    }

    @Test
    public void testInstantiationWithJar() {
        DeepSparkContext sc = new DeepSparkContext("local", "myapp1", "/tmp", "");
        assertNotNull(sc);

        RDD deepMongoRDD = sc.createRDD(new CellDeepJobConfigMongoDB());

//        assertTrue(deepMongoRDD instanceof MongoCellRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoEntityRDD);


        deepMongoRDD = sc.createRDD(new EntityDeepJobConfigMongoDB(BookEntity.class));

//        assertTrue(deepMongoRDD instanceof MongoEntityRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoCellRDD);

        sc.stop();
    }

    @Test
    public void testInstantiationWithJars() {
        DeepSparkContext sc = new DeepSparkContext("local", "myapp1", "/tmp", new String[]{"", ""});
        assertNotNull(sc);

        RDD deepMongoRDD = sc.createRDD(new CellDeepJobConfigMongoDB());

//        assertTrue(deepMongoRDD instanceof MongoCellRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoEntityRDD);


        deepMongoRDD = sc.createRDD(new EntityDeepJobConfigMongoDB(BookEntity.class));

//        assertTrue(deepMongoRDD instanceof MongoEntityRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoCellRDD);

        sc.stop();
    }

    @Test
    public void testInstantiationWithJarsAndEnv() {
        Map<String, String> env = new HashMap<>();

        DeepSparkContext sc = new DeepSparkContext("local", "myapp1", "/tmp", new String[]{"", ""}, env);

        assertNotNull(sc);
        RDD deepMongoRDD = sc.createRDD(new CellDeepJobConfigMongoDB());

//        assertTrue(deepMongoRDD instanceof MongoCellRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoEntityRDD);


        deepMongoRDD = sc.createRDD(new EntityDeepJobConfigMongoDB(BookEntity.class));

//        assertTrue(deepMongoRDD instanceof MongoEntityRDD);
//
//        assertFalse(deepMongoRDD instanceof MongoCellRDD);


        sc.stop();
    }
}