package com.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExampleOfSearchPageWithModulateQuery {

	private static ResultSet resultSet;
	private static String idMovieColumn;
	private static String titleMovieColumn;
	private static String directorMovieColumn;
	private static String synopsisMovieColumn;
	private static int NUM = 0;
	private static List<String> tableColumns = new ArrayList<>();
	private static List<String> tableValue = new ArrayList<>();

    enum QueryStruct {
        ID, TITLE, DIRECTOR, SYNOPSIS
    }

    static {
        try {
            String url = "jdbc:mysql://localhost:3306/HIBERNATE?autoReconnect=true&useSSL=false";
            Statement stmt = null;
            Connection conn = DriverManager.getConnection(url, "root", "R!nald!1976");
            try {
                System.out.println(conn.isValid(10));
                stmt = conn.createStatement();

            } catch (SQLException e) {
                e.printStackTrace();
			}
			resultSet = stmt.executeQuery("SELECT * FROM HIBERNATE.MOVIES;");
			System.out.println(resultSet.isClosed());
			ResultSetMetaData rsm = resultSet.getMetaData();
			NUM = rsm.getColumnCount();
			int i = 0;
			System.out.println(10);
			while (i++ <= NUM) {
				if (1 == i) {
					idMovieColumn = rsm.getColumnName(i);
					System.out.println(idMovieColumn + " " + i);
					tableColumns.add(idMovieColumn);
				}
				if (2 == i) {
					titleMovieColumn = rsm.getColumnName(i);
					System.out.println(titleMovieColumn + " " + i);
					tableColumns.add(titleMovieColumn);
				}
				if (3 == i) {
					directorMovieColumn = rsm.getColumnName(i);
					System.out.println(directorMovieColumn + " " + i);
					tableColumns.add(directorMovieColumn);
				}
				if (4 == i) {
					synopsisMovieColumn = rsm.getColumnName(i);
					System.out.println(synopsisMovieColumn + " " + i);
					tableColumns.add(synopsisMovieColumn);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		mvcSearchPage();
	}

	private static String mvcSearchPage() {
        StringBuilder query = new StringBuilder("select * ");
        String idTemp;
        String titleTemp;
        String directorTemp;
        String synopsisTemp;
        int i = 0;
        try (InputStreamReader isr = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(isr)) {

            while (i++ <= NUM) {
                if (1 == i) {
                    System.out.println("Insert into " + tableColumns.get(i - 1) + " a value: ");
                    idTemp = br.readLine();
                    if (idTemp.equals("")) {
                        System.out.println(tableColumns.get(i - 1) + " can't be null" + "\nInsert into "
                                + tableColumns.get(i - 1) + " a value: ");
                        idTemp = br.readLine();
                    }
					tableValue.add(idTemp);
					query.append(tableColumns.get(i - 1));
				}
				if (2 == i) {
					System.out.println("Insert into " + tableColumns.get(i - 1) + " a value: ");
					titleTemp = br.readLine();
					if (!"".equals(titleTemp)) {
						tableValue.add(titleTemp);
					}
				}
				if (3 == i) {
					System.out.println("Insert into " + tableColumns.get(i - 1) + " a value: ");
					directorTemp = br.readLine();
					if (!"".equals(directorTemp)) {
						tableValue.add(directorTemp);
					}
				}
				if (4 == i) {
					System.out.println("Insert into " + tableColumns.get(i - 1) + " a value: ");
					synopsisTemp = br.readLine();
					if (!"".equals(synopsisTemp)) {
						tableValue.add(synopsisTemp);
					}

				}

				// System.out.println("error!\nenter a number between 0 and 5:
				// ");
				// num = in.nextLine();
			}
			System.out.println("Bye");
			br.close();// essenziale chiudere lo stre
		} catch (IOException e) {
			e.printStackTrace();
		}
		return query.toString();
	}

	private static void build() {

	}
}
