using System;

namespace Project3
{
	class MainClass
	{
		private static string start_date = "2009-06-22 00:00:00";
		private static string end_date = "2009-06-22 23:59:59";
		private static string[] check_words = {"NSA", "National Security Agency", "Obama", "USA", "Snowden"};
		public static void Main (string[] args)
		{
			String text = loadFile(@"ciphertext_sheet3.txt");
			long start = getTimestamp (start_date);
			long end = getTimestamp (end_date);

			// Running through all the dates
			for (long i = start; i < end; i++) {

				foreach (char c in text) {
					// TODO
				}
			}
		}

		public static bool check_text()
		{
			String text = loadFile(@"decrypttext_sheet3.txt");
			foreach (string w in text.Split(' ')) 
			{
				/*if (check_words.Any (w.Contains)) // Needs to check if word (w) is in check_words 
				{
					return true;
				}*/
			}
			return false;
		}

		public static int[] generateKeys(int i) 
		{
			int[] k;
			for (int j = 0; j < 16; j++) 
			{
				// TODO
			}
			return k;
		}

		public static double updateFunction(int s) 
		{
			return (69.069 * s + 5) % Math.Pow (2.0, 32.0);
		}

		public static int encryption(char c, int[] k, int i)
		{
			return Convert.ToInt32(c) ^ k[i % 16];
		}

		public static String loadFile(String path) 
		{
			return System.IO.File.ReadAllText(path);

		}

		public static long getTimestamp(String date)
		{
			//return Convert.ToDateTime (date).; // Needs to convert string to date to timestamp.
		}
	}
}
