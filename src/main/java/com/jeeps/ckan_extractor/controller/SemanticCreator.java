package com.jeeps.ckan_extractor.controller;

import com.jeeps.ckan_extractor.model.CkanPackage;
import com.jeeps.ckan_extractor.model.CkanResource;
import com.jeeps.ckan_extractor.vocabs.GVLD;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DCAT;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.SKOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SemanticCreator {
    private Model mModel;
    private final FileOutputStream os;
    public static final String DATA_PREFIX = "http://example.org/data/";

    public SemanticCreator() throws FileNotFoundException {
        // Create model
        mModel = ModelFactory.createDefaultModel();
        // File dump
        File fos = new File("triples.rdf");
        os = new FileOutputStream(fos);
        initializeVocabs();
    }

    private void initializeVocabs() {
        // Data prefix
        mModel.setNsPrefix("data", DATA_PREFIX);

        // Foaf prefix
        String foaf = FOAF.getURI();
        mModel.setNsPrefix("foaf",foaf);

        // Dcat prefix
        String dcat = DCAT.getURI();
        mModel.setNsPrefix("dcat", dcat);

        // DCTerms prefix
        String dct = DCTerms.getURI();
        mModel.setNsPrefix("dct", dct);

        // SKOS prefix
        String skos = SKOS.getURI();
        mModel.setNsPrefix("skos", skos);

        // DBO prefix
        String dbo = "";


        // Our Ontology prefix
        String vocabPrefix = GVLD.getURI();
        mModel.setNsPrefix("gvld",vocabPrefix);
    }

    public void generateTriples(CkanPackage aPackage, CkanResource[] resourcesCkan) {
        // Create package as Catalog
        Resource catalog = mModel.createResource(DATA_PREFIX + urlify(aPackage.getName()))
                .addProperty(RDF.type, GVLD.Catalog);

        if (exists(aPackage.getAuthor()))
            catalog.addProperty(DCTerms.publisher, mModel.createResource(DATA_PREFIX + urlify(aPackage.getName() + "_publisher"))
                            .addProperty(RDF.type, FOAF.Agent)
                            .addProperty(FOAF.name, aPackage.getAuthor()))
                    .addProperty(GVLD.isPrivate, Boolean.toString(aPackage.isPrivate()));
        if (exists(aPackage.getIssued()))
            catalog.addProperty(DCTerms.issued, aPackage.getIssued());
        if (exists(aPackage.getVersion()))
            catalog.addProperty(GVLD.version, aPackage.getVersion());
        if (exists(aPackage.getTitle()))
            catalog.addProperty(DCTerms.title, aPackage.getTitle());
        if (exists(aPackage.getDescription()))
            catalog.addProperty(DCTerms.description, aPackage.getDescription());
        if (exists(aPackage.getState()))
            catalog.addProperty(GVLD.status, aPackage.getState());
        if (exists(aPackage.getModified()))
            catalog.addProperty(DCTerms.modified, aPackage.getModified());
        if (exists(aPackage.getLicense_title()))
            catalog.addProperty(GVLD.license, aPackage.getLicense_title());

        // Write model to file
        RDFWriter writer = mModel.getWriter("RDF/XML");
        writer.write(mModel, os,  "");
    }

    public String urlify(String string) {
        return string.trim().toLowerCase()
                .replaceAll(" ", "_")
                .replaceAll("_", "_")
                .replaceAll("\"", "")
                .replaceAll("\\.", "_")
                .replaceAll("\n", "_");
    }

    private boolean exists(String string) {
        if (string == null)
            return false;
        return !string.isBlank() && !string.isEmpty();
    }
}
