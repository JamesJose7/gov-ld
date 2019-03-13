package com.jeeps.gov_ld.vocabs;

import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class GVLD {
    private static Model M_MODEL = ModelFactory.createDefaultModel();

    public GVLD() {
        M_MODEL = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        String gvldOntologyPath = "gvld.owl";
        M_MODEL.read(gvldOntologyPath);
        M_MODEL.write(System.out);
    }

    // Namespace
    public static final String NS = "http://www.government-linked-data.org/";

    public static String getURI() {return NS;}

    // Namespace as a resource
    public static final Resource NAMESPACE = M_MODEL.getResource( NS );

    /* Properties */
    public static final Property version = M_MODEL.getProperty(NS + "version");
    public static final Property isPrivate = M_MODEL.getProperty(NS + "isPrivate");
    public static final Property status = M_MODEL.getProperty(NS + "status");
    public static final Property license = M_MODEL.getProperty(NS + "license");
    public static final Property isPartOf = M_MODEL.getProperty(NS + "isPartOf");
    public static final Property organization = M_MODEL.getProperty(NS + "organization");
    public static final Property tag = M_MODEL.getProperty(NS + "tag");
    public static final Property group = M_MODEL.getProperty(NS + "group");
    public static final Property title = M_MODEL.getProperty(NS + "title");
    public static final Property description = M_MODEL.getProperty(NS + "description");
    public static final Property politicalLevel = M_MODEL.getProperty(NS + "politicalLevel");
    public static final Property mediaType = M_MODEL.getProperty(NS + "mediaType");

    /* Classes */
    public static final Resource Catalog = M_MODEL.getResource(NS + "Catalog");
    public static final Resource Group = M_MODEL.getResource(NS + "Group");
    public static final Resource Tag = M_MODEL.getResource(NS + "Tag");
    public static final Resource Organization = M_MODEL.getResource(NS + "Organization");
    public static final Resource Dataset = M_MODEL.getResource(NS + "Dataset");
    public static final Resource Government = M_MODEL.getResource(NS + "Government");
}
