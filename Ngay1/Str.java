	package Ngay1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Str {
		private String string;
		
		public Str(String string) {
			this.string = string;
		}
		
		public void deleteCharacter(char Char) {
			 string = string.chars()
                    .filter(c -> c != Char)
                    .mapToObj(c -> String.valueOf((char)c))
                    .collect(Collectors.joining());
		}
		
		public void deleteCharacter1(char Char) {
			 string = string.replace(String.valueOf(Char), "");
			 //string = string.replace(Character.toString(Char), "");
		}
		
		public String reverseString() {
			return new StringBuilder(string)
					.reverse()
					.toString();
		}
		
		public String convString() {
			return IntStream.of(72,101)
					.mapToObj(ch -> String.valueOf((char)ch))
					.collect(Collectors.joining());
		}
		public String insertString(String stringInsert) {
			return new StringBuilder(string)
					.insert(string.length()+1, stringInsert)
					.toString();
		}
		
		public boolean isValidString() {
			return !string.matches(".*[a-zA-Z].*");
		}
		
		public int numberOfNumber(String[] stringArray) {
			return stringArray.length;
		}
		
		public static boolean isValidNumber(String s) {
	        return s.matches("-?\\d+(\\.\\d+)?");
	    }

	    public static String checkStringNumbers(String input) {
	        String[] parts = input.split("\\s+"); // Tách chuỗi theo dấu cách
	        long count = Arrays.stream(parts).filter(Str::isValidNumber).count();

	        return count == parts.length ? "true, có " + count + " số." : "false";
	    }
		
	}
