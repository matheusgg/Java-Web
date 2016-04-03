package br.com.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient("localhost", 27017);
		DB db = client.getDB("app_db");
		System.out.println(db.getCollectionNames());
		// DBCollection clienteCollection = db.getCollection("clientes");

		/*
		 * Insert
		 */
		// BasicDBObject cliente = new BasicDBObject();
		// cliente.put("nome", "Cliente 1");
		// clienteCollection.save(cliente);
		// System.out.println(cliente.get("_id"));

		/*
		 * Find
		 */
		// BasicDBObject query = new BasicDBObject("_id", cliente.get("_id"));
		// DBCursor cursor = clienteCollection.find(query);
		// while (cursor.hasNext()) {
		// System.out.println(cursor.next());
		// }

		/*
		 * Update
		 */
		// BasicDBObject query = new BasicDBObject("id", 1);
		// BasicDBObject cliente = new BasicDBObject("nome", "Matheus");
		// System.out.println(clienteCollection.update(query, cliente));

		/*
		 * Operations
		 */
		// for (int i = 0; i < 10000; i++) {
		// BasicDBObject row = new BasicDBObject();
		// row.put("nome", "Cliente " + i);
		// clienteCollection.insert(row);
		// }

		// DBCursor cursor = clienteCollection.find();
		// while (cursor.hasNext()) {
		// System.out.println(cursor.next());
		// }
		//
		// System.out.println(clienteCollection.count());

		System.out.println("OK");
		client.close();
	}

}
