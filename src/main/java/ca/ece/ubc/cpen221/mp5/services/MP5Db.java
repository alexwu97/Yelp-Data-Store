package ca.ece.ubc.cpen221.mp5.services;

import java.util.function.ToDoubleBiFunction;;

// This interface represents a database of objects of type T. 
// It supports querying for objects from the database as well
// as two basic statistical learning operations on the database.

public interface MP5Db<T> {
	/**
	 * 
	 * @param user
	 *            represents a user_id in the database
	 * @return a function that predicts the user's ratings for objects (of type
	 *         T) in the database of type MP5Db<T>. The function that is
	 *         returned takes two arguments: one is the database and other other
	 *         is a String that represents the id of an object of type T.
	 */
	ToDoubleBiFunction<MP5Db<T>, String> getPredictorFunction(String user);
}
