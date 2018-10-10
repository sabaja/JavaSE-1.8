package com.csv;

public class CsvSimpleSolution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class CsvReader{
	private static String path;
	
	
	public CsvReader(){
		
	}
	
	public static void printCsv(String fileName) {
		
	}
	
}

enum Files{
	
	Path("/home/sabaja/Scrivania/workspace/JavaSE-1.8/src.study/com/csv/csv_dir");
	
	private String path;
	private Files(String path){
		this.path = path;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
}