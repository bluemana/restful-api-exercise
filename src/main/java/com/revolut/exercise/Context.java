package com.revolut.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.core.User;

public enum Context {

	INSTANCE;
	
	private static final Logger LOGGER = Logger.getLogger(Context.class);
	
	private final ExecutorService executor;
	private final Map<Integer, User> users;
	private int userId;
	private final Map<Integer, Transaction> transactions;
	private int transactionId;
	
	private Context() {
		executor = Executors.newSingleThreadExecutor();
		users = new HashMap<Integer, User>();
		userId = 0;
		transactions = new HashMap<Integer, Transaction>();
		transactionId = 0;
	}
	
	public Collection<User> getUsers() {
		Collection<User> result = Collections.emptyList();
		try {
			result = executor.submit(new Callable<Collection<User>>() {

				@Override
				public Collection<User> call() throws Exception {
					return new ArrayList<User>(users.values());
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	public User createUser(String name, int balance) {
		User result = null;
		try {
			result = executor.submit(new Callable<User>() {

				@Override
				public User call() throws Exception {
					User user = new User(++userId, name, balance);
					users.put(user.getId(), user);
					return user;
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	public User getUser(int id) {
		User result = null;
		try {
			result = executor.submit(new Callable<User>() {

				@Override
				public User call() throws Exception {
					return users.get(id);
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	public void clear() {
		try {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					users.clear();
					userId = 0;
					transactions.clear();
					transactionId = 0;
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public Collection<Transaction> getTransactions() {
		Collection<Transaction> result = Collections.emptyList();
		try {
			result = executor.submit(new Callable<Collection<Transaction>>() {

				@Override
				public Collection<Transaction> call() throws Exception {
					return new ArrayList<Transaction>(transactions.values());
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	public Transaction transact(int sourceUserId, int destinationUserId, int amount) {
		Transaction result = null;
		try {
			result = executor.submit(new Callable<Transaction>() {

				@Override
				public Transaction call() throws Exception {
					User sourceUser = users.get(sourceUserId);
					User destinationUser = users.get(destinationUserId);
					if (sourceUser != null && destinationUser != null && sourceUser.getBalance() >= amount) {
						sourceUser = new User(sourceUser.getId(), sourceUser.getName(), sourceUser.getBalance() - amount);
						destinationUser = new User(destinationUser.getId(), destinationUser.getName(), destinationUser.getBalance() + amount);
						users.put(sourceUserId, sourceUser);
						users.put(destinationUserId, destinationUser);
						Transaction transaction = new Transaction(++transactionId, sourceUserId, destinationUserId, amount);
						transactions.put(transaction.getId(), transaction);
						return transaction;
					} else {
						throw new IllegalArgumentException("Invalid transaction");
					}
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
}
