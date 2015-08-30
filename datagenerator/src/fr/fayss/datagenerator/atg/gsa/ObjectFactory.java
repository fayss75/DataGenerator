//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.08.30 à 11:04:29 AM CEST 
//


package fr.fayss.datagenerator.atg.gsa;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.fayss.datagenerator.atg.gsa package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.fayss.datagenerator.atg.gsa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Dataconfig }
     * 
     */
    public Dataconfig createDataconfig() {
        return new Dataconfig();
    }

    /**
     * Create an instance of {@link Dataconfig.MainGenerators }
     * 
     */
    public Dataconfig.MainGenerators createDataconfigMainGenerators() {
        return new Dataconfig.MainGenerators();
    }

    /**
     * Create an instance of {@link Dataconfig.DataGenerator }
     * 
     */
    public Dataconfig.DataGenerator createDataconfigDataGenerator() {
        return new Dataconfig.DataGenerator();
    }

    /**
     * Create an instance of {@link PropertyValue }
     * 
     */
    public PropertyValue createPropertyValue() {
        return new PropertyValue();
    }

    /**
     * Create an instance of {@link PropertyConfig }
     * 
     */
    public PropertyConfig createPropertyConfig() {
        return new PropertyConfig();
    }

}
