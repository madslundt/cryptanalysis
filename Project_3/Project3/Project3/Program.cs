using System;

namespace Project3
{
	class MainClass
	{
		private static string[] date = 	{"2009-06-22 00:00:00", "2009-06-23 00:00:00", "2009-06-24 00:00:00", 
										"2009-06-25 00:00:00", "2009-06-26 00:00:00", 
										"2009-06-27 00:00:00", "2009-06-28 23:59:59"};
		private static string[] known_words = {"NSA", "National Security Agency", "Obama", "USA", "Snowden"};
		public static void Main (string[] args)
		{
			String[] lines = loadFile(@"ciphertext_sheet3.txt");
			foreach (string line in lines)
			{
				// Use a tab to indent each line of the file.
				Console.WriteLine("\t" + line);
			}
		}

		public static String updateFunction(String line) 
		{
			return line;
		}

		public static String decryption(String line)
		{
			return line;
		}

		public static String[] loadFile(String path) 
		{
			return System.IO.File.ReadAllLines(path);

		}

		public static String GetTimestamp(DateTime value)
		{
			return value.ToString("yyyy-MM-dd HH:mm:ss");
		}
	}
}
