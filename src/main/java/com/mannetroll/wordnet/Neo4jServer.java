package com.mannetroll.wordnet;

import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.neo4j.server.configuration.Configurator;
import org.neo4j.server.configuration.ServerConfigurator;
import org.neo4j.shell.ShellSettings;

public class Neo4jServer {

    public static void main(String args[]) {
        GraphDatabaseAPI graphdb = (GraphDatabaseAPI) new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(
                "target/wordnet.db").setConfig(ShellSettings.remote_shell_enabled, "true").newGraphDatabase();

        // let the server endpoint be on a custom port
        ServerConfigurator config = new ServerConfigurator(graphdb);
        config.configuration().setProperty(Configurator.WEBSERVER_PORT_PROPERTY_KEY, 7575);

        WrappingNeoServerBootstrapper srv = new WrappingNeoServerBootstrapper(graphdb, config);
        srv.start();
    }
}
