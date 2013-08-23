package com.rdlin.RPM.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.rdlin.RPM.util.Util;

public class Ui{
	Util util = new Util();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public void run() throws IOException {
		String keyword = " ";
		// information storage
		List<String> services = new ArrayList<String>();
		List<String> usernames = new ArrayList<String>();
		List<String> passwords = new ArrayList<String>();
		Hashtable<String, List<String>> servicesToAccount = new Hashtable<String, List<String>>();
		//read user input
		InputStreamReader istream = new InputStreamReader(System.in);
        BufferedReader bufRead = new BufferedReader(istream);
        printStartOptions();
        boolean quit=false;
        while(!quit){
        	String input = bufRead.readLine();
        	if(input.equals("0")){
        		quit=true;
        	}
        	if(input.equals("1")){
        		System.out.println("Please enter service name: ");
	            String service = bufRead.readLine();
	            String username="";
	            String password="";
	            System.out.print("Service        Username       Password            \n");
	            System.out.print("-------        --------       --------            \n");
	            for(int i = 0; i<services.size(); i++){
	            	if (services.get(i).contains(service)){
	            		service=services.get(i);
	            		List<String> account = servicesToAccount.get(services.get(i));	
	            		username=util.decode(account.get(0), keyword);
	            		password=util.decode(account.get(1), keyword);
	            		//output
	            		System.out.print(service);
	            		for(int s = 0; s<15-service.length(); s++){System.out.print(" ");}
	            		System.out.print(username);
	            		for(int s = 0; s<15-username.length(); s++){System.out.print(" ");}
	            		System.out.print(password+"\n");
	            	}
	            }
        	}
        	if(input.equals("2")){//add handling for entering in an existing service name
        		if(keyword == " "){
        			System.out.println("Please enter in a valid keyword first: ");
        			keyword = bufRead.readLine();
        		}
		        try {
		             System.out.println("Please Enter In Your Service Name: ");
		             String service = bufRead.readLine();
		             
		             System.out.println("Please Enter In The Username: ");
		             String username = bufRead.readLine();
		
		             System.out.println("Please Enter In The Password: ");
		             String password = bufRead.readLine();
		             
		             
		             String encodedUsername = util.encode(username, keyword);
		             String encodedPassword = util.encode(password, keyword);

		             services.add(service);
		             usernames.add(encodedUsername);
		             passwords.add(encodedPassword);
		             servicesToAccount.put(service, Arrays.asList(encodedUsername, encodedPassword));
		        }
		        catch (IOException err) {
		             System.out.println("Error reading line");
		        }
        	}
        	if(input.equals("3")){
        		System.out.println("Please enter in file location: ");
	            String location = bufRead.readLine();
        		List<String> fileText = Util.readLines(location);
        		for(int i = 0; i<fileText.size()/3;i++){
        			services.add(fileText.get(i*3+0));
        			usernames.add(fileText.get(i*3+1));
        			passwords.add(fileText.get(i*3+2));
        			servicesToAccount.put(fileText.get(i*3+0), Arrays.asList(fileText.get(i*3+1), fileText.get(i*3+2)));
        		}
        	}
        	if(input.equals("4")){
        		//will add later once more features for choosing service to delete are implemented
        	}
        	if(input.equals("5")){
        		//Add ability to choose which service to delete if multiple matches
        		System.out.println("Enter in name of service you want to delete: ");
	            String service = bufRead.readLine();
	            for(int i = 0; i<services.size(); i++){
	            	if(services.get(i).contains(service)){
	            		services.remove(i);
	            		usernames.remove(i);
	            		passwords.remove(i);
	            		servicesToAccount.remove(services.get(i));
	            	}
	            }
        	}
        	if(input.equals("6")){
        		System.out.println("Please enter in new keyword: ");
	            keyword = bufRead.readLine();
	            System.out.println("Keyword changed to: " + keyword);
        	}
        	if(input.equals("7")){
        		String service="";
        		String username="";
	            String password="";
	            System.out.print("Service        Username       Password            \n");
	            System.out.print("-------        --------       --------            \n");
	            for(int i = 0; i<services.size(); i++){
	            		service=services.get(i);
	            		List<String> account = servicesToAccount.get(services.get(i));	
	            		username=util.decode(account.get(0), keyword);
	            		password=util.decode(account.get(1), keyword);
	            		//output
	            		System.out.print(service);
	            		for(int s = 0; s<15-service.length(); s++){System.out.print(" ");}
	            		System.out.print(username);
	            		for(int s = 0; s<15-username.length(); s++){System.out.print(" ");}
	            		System.out.print(password+"\n");
	            }
        	}
        	if(input.equals("8")){
        		String fileText="";
        		String fileName="C:/Users/Robert/Desktop/testpasswords.txt";
        		System.out.println("Default save location is: " + fileName);
        		System.out.println("To change, please type it in now, else, press enter");
        		String newFileName = bufRead.readLine();
        		if(!newFileName.equals("")){
        			fileName=newFileName;
        		}
        		for(int i = 0; i < services.size(); i++){
        			fileText=fileText+services.get(i)+"\n"+usernames.get(i)+"\n"+passwords.get(i)+"\n";
        		}
        		try {
         
        			File file = new File("C:/Users/Robert/Desktop/testpasswords.txt");
         
        			if (!file.exists()) {
        				System.out.println("File does not exist");
        			}
        			else{
	        			FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        			BufferedWriter bw = new BufferedWriter(fw);
	        			bw.write(fileText);
	        			bw.close();
	         
	        			System.out.println("Done");
        			}
         
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        	//input = bufRead.readLine();
        	printStartOptions();
        }

	}
	public void printStartOptions(){
		System.out.println("1: Search for login information");
		System.out.println("2: Add new entries");
		System.out.println("3: Read from file");
		System.out.println("4: Edit entries");
		System.out.println("5: Delete entries");
		System.out.println("6: Change keyword");
		System.out.println("7: Display all accounts");
		System.out.println("8: Save/Write to File");
		System.out.println("0: Exit");
	}

}

